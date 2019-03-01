package com.group5.definitions.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group5.definitions.model.Answer;
import com.group5.definitions.model.Concept;
import com.group5.definitions.model.Justification;
import com.group5.definitions.model.Question;
import com.group5.definitions.model.User;
import com.group5.definitions.services.AnswerService;
import com.group5.definitions.services.ChapterService;
import com.group5.definitions.services.ConceptService;
import com.group5.definitions.services.JustificationService;
import com.group5.definitions.services.QuestionService;
import com.group5.definitions.usersession.Tab;
import com.group5.definitions.usersession.UserSessionService;
import com.group5.definitions.utilities.QuestionGenerator;
import com.group5.definitions.utilities.QuestionMarker;

@Controller
public class ConceptController {

	@Autowired
	private ChapterService chapterService;

	@Autowired
	private ConceptService conceptService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserSessionService userSession;

	@Autowired
	private QuestionGenerator questionGenerator;

	@Autowired
	private QuestionMarker questionMarker;

	@Autowired
	private JustificationService justificationService;
	private final int DEFAULT_SIZE = 1;

	@ModelAttribute
	public void addUserToModel(Model model) {
		userSession.addUserToModel(model);
	}

	@RequestMapping("/concept/{id}")
	public String conceptPage(Model model, HttpServletRequest req, @PathVariable long id,
			@RequestParam(name = "close", required = false) Long closeTab, HttpServletResponse httpServletResponse,
			@PageableDefault(size = DEFAULT_SIZE, sort = {"id"}) Pageable page) throws IOException {
		
		Concept concept = conceptService.findById(id);
		String name = concept.getConceptName();
		// If close tab button was pressed, remove the tab
		if (closeTab != null) {
			userSession.removeTab(closeTab);
			// If user is in the tab that is being closed, redirect to home page
			if (closeTab.equals(id)) {
				httpServletResponse.sendRedirect("/");
				return null;
			}
		}
		// Open a new tab if it doesn't exist.
		Tab tab = new Tab(id, name, "/concept/" + id, true);
		if (!userSession.getOpenTabs().contains(tab))
			userSession.addTab(tab);
		else
			userSession.setActive(name);
		model.addAttribute("tabs", userSession.getOpenTabs());
		model.addAttribute("conceptName", name);
		model.addAttribute("conceptId", id);
		// if user is a teacher get all answers and return the teacher template
		User user;
		if (req.isUserInRole("ROLE_TEACHER")) {
			Page<Answer> markedAnswers = answerService.findByMarkedAndConceptId(true, id, page);
			Page<Answer> unmarkedAnswers = answerService.findByMarkedAndConceptId(false, id, page);
			String url = concept.getURL();
			model.addAttribute("conceptURL", url);
			model.addAttribute("markedAnswers", markedAnswers);
			model.addAttribute("unmarkedAnswers", unmarkedAnswers);
			return "teacher";
		} else {
			user = userSession.getLoggedUser();
			model.addAttribute("diagramInfo", chapterService.generateDiagramInfoStudent(user));
			addQuestionInfoToModel(model, concept);
			return "concept";
		}
	}

	private void addQuestionInfoToModel(Model model, Concept concept) {
		Question question = questionGenerator.generateQuestion(concept);
		Answer answer = question.getAnswer();
		Justification justification = question.getJustification();
		if (answer != null)
			model.addAttribute("answerId", answer.getId());
		if (justification != null)
			model.addAttribute("justificationId", justification.getId());
		model.addAttribute("questionText", question.getQuestionText());
		model.addAttribute("openQuestion", !question.isYesNoQuestion());
		model.addAttribute("questionType", question.getType());
	}

	@RequestMapping("/concept/{conceptId}/loadUnmarkedQuestions")
	public String loadUnmarkedQuestions(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page, @PathVariable long conceptId) {
		Page<Question> unmarkedQuestions = questionService.findByMarkedAndAnswer_Concept_IdAndUser(false, conceptId,
				userSession.getLoggedUser(), page);
		model.addAttribute("questions", unmarkedQuestions);
		return "showquestion";
	}
	
	@RequestMapping("/concept/{conceptId}/loadMarkedQuestions")
	public String loadMarkedQuestions(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page, @PathVariable long conceptId) {
		Page<Question> markedQuestions = questionService.findByMarkedAndAnswer_Concept_IdAndUser(true, conceptId,
				userSession.getLoggedUser(), page);
		model.addAttribute("questions", markedQuestions);
		return "showquestion";
	}
	
	@RequestMapping("/concept/{conceptId}/loadUnmarkedAnswers")
	public String loadUnmarkedAnswers(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page, @PathVariable long conceptId) {
		Page<Answer> unmarkedAnswers = answerService.findByMarkedAndConceptId(false, conceptId, page);
		model.addAttribute("unmarkedAnswers", unmarkedAnswers);
		return "showAnswerUnmarked";
	}
	
	@RequestMapping("/concept/{conceptId}/loadMarkedAnswers")
	public String loadMarkedAnswers(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page, @PathVariable long conceptId) {
		Page<Answer> markedAnswers = answerService.findByMarkedAndConceptId(true, conceptId, page);
		model.addAttribute("markedAnswers", markedAnswers);
		return "showAnswerMarked";
	}
	
	@PostMapping("/concept/{conceptId}/mark/{answerId}")
	public String markAnswer(Model model, @PathVariable long conceptId, @PathVariable long answerId,
			@RequestParam String correct,
			@RequestParam(required=false) String justificationTextNew,
			HttpServletResponse httpServletResponse) throws IOException {
		Answer ans = answerService.getOne(answerId);
		ans.setMarked(true);
		ans.setCorrect(correct.equals("yes"));
		answerService.save(ans);
		if ((justificationTextNew!=null) && (correct.equals("no"))) {
			Justification justification = new Justification(justificationTextNew.toUpperCase(), true, userSession.getLoggedUser());
			justification.setValid(true);
			justification.setAnswer(ans);
			justificationService.save(justification);
		}
		for (Question q : ans.getQuestions()) {
			if (!q.isMarked() && (q.getType()==0)) {
				q.setMarked(true);
				q.setCorrect(correct.equals("yes"));
				questionService.save(q);
			}
		}
		httpServletResponse.sendRedirect("/concept/" + conceptId);
		return null;
	}

	@RequestMapping("/delete/concept/{conceptId}/answer/{id}")
	public String deleteAnswer(Model model, @PathVariable String conceptId, @PathVariable long id,
			HttpServletResponse httpServletResponse) throws IOException {
		answerService.deleteById(id);
		httpServletResponse.sendRedirect("/concept/" + conceptId);
		return null;
	}

	@PostMapping("/modifyAnswer/concept/{conceptId}/answer/{id}")
	public String addModifiedAnswer(Model model, @PathVariable String conceptId, @PathVariable long id,
			@RequestParam String answerText, @RequestParam(value = "correct", required = false) String cAnswer,
			@RequestParam(value = "justificationTextNew", required = false) String justificationText,
			@RequestParam(value = "validNew", required = false) String jValid,
			@RequestParam(value = "errorNew", required = false) String error, HttpServletResponse httpServletResponse)
			throws IOException {
		Answer ans = answerService.getOne(id);
		ans.setAnswerText(answerText);
		Justification newJ = null;
		if (cAnswer != null) {
			ans.setCorrect(cAnswer.equals("yes"));
			if (cAnswer.equals("yes")) {
				//It is needed to delete the justifications from the DB
				for(Justification j: ans.getJustifications()) {
					justificationService.deleteById(j.getId());
				}
				ans.getJustifications().clear(); //In case, we clear the answer justifications
			} else {
				if(ans.getJustifications().size() == 0) {
					newJ = new Justification(justificationText, true, userSession.getLoggedUser());
					newJ.setValid(jValid.equals("yes"));
					if (jValid.equals("yes"))
						newJ.setError(error);
					ans.addJustification(newJ);
				}
			}
		}
		answerService.save(ans);
		if (newJ != null) {
			newJ.setAnswer(ans);
			justificationService.save(newJ);
		}
		httpServletResponse.sendRedirect("/concept/" + conceptId);
		return null;
	}

	@PostMapping("/concept/{conceptId}/addAnswer")
	public String addAnswer(Model model, @PathVariable long conceptId,
			@RequestParam String answerText,
			@RequestParam String correct,
			@RequestParam(required = false) String justificationText,
			@RequestParam(required = false) String validity,
			@RequestParam(required = false) String error,
			HttpServletResponse httpServletResponse) throws IOException {
		Concept c = conceptService.findById(conceptId);
		User user = userSession.getLoggedUser();
		Answer answer = new Answer(answerText.toUpperCase(), true, user, c);
		answer.setCorrect(correct.equals("yes"));
		answerService.save(answer);
		if ((justificationText!=null) && (correct.equals("no"))) {
			Justification justification = new Justification(justificationText.toUpperCase(), true, user);
			justification.setValid(validity.equals("yes"));
			if ((error!=null) && (validity.equals("no"))) {
				justification.setError(error.toUpperCase());
			}
			justification.setAnswer(answer);
			justificationService.save(justification);
		}
		httpServletResponse.sendRedirect("/concept/" + conceptId);
		return null;
	}

	@PostMapping("/saveAnswer/concept/{conceptId}")
	public String saveAnswer(Model model, @PathVariable long conceptId, HttpServletResponse httpServletResponse,
			@RequestParam String questionText, @RequestParam int questionType,
			@RequestParam(required = false) String answerText, @RequestParam(required = false) String answerOption,
			@RequestParam(required = false) Long answerQuestionId,
			@RequestParam(required = false) Long justificationQuestionId) throws IOException {
		boolean open = answerText != null;
		String answerFinalText = open ? answerText : answerOption;
		questionMarker.saveQuestion(conceptService.findById(conceptId), answerFinalText, questionText,
				questionType, answerQuestionId, justificationQuestionId);
		httpServletResponse.sendRedirect("/concept/" + conceptId);
		return null;
	}

	@PostMapping("/concept/{conceptId}/saveURL")
	public void saveURL(Model model, @PathVariable Long conceptId, @RequestParam String url,
			HttpServletResponse httpServletResponse) throws IOException {
		conceptService.saveURL(conceptId, url);
		httpServletResponse.sendRedirect("/concept/" + conceptId);
	}

	@RequestMapping("/deleteJust/concept/{conceptId}/justification/{id}")
	public void deleteJustification(Model model, @PathVariable String conceptId, @PathVariable long id,
			HttpServletResponse httpServletResponse) throws IOException {
		justificationService.deleteById(id);
		httpServletResponse.sendRedirect("/concept/" + conceptId);
	}
	
	@RequestMapping("/modifyJust/concept/{conceptId}/justification/{id}")
	public void modifyJustification(Model model, @PathVariable String conceptId, @PathVariable String id,
			@RequestParam String jText, @RequestParam String valid, @RequestParam(required = false) String error,
			HttpServletResponse httpServletResponse) throws IOException{
		Justification j = justificationService.findById(Long.parseLong(id));
		j.setJustificationText(jText);
		if(valid.equals("yes")) {
			j.setValid(true);
		}else {
			j.setValid(false);
			j.setError(error);
		}
		justificationService.save(j);
		httpServletResponse.sendRedirect("/concept/" + conceptId);
	}
}
