package com.group5.definitions.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	private final int DEFAULT_SIZE = 10;

	@ModelAttribute
	public void addUserToModel(Model model) {
		userSession.addUserToModel(model);
	}

	@RequestMapping("/concept/{name}")
	public String conceptPage(Model model, HttpServletRequest req, @PathVariable String name,
			@RequestParam(name = "close", required = false) String closeTab, HttpServletResponse httpServletResponse,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page) throws IOException {
		// If close tab button was pressed, remove the tab
		if (closeTab != null) {
			userSession.removeTab(closeTab);
			// If user is in the tab that is being closed, redirect to home page
			if (name.equals(closeTab)) {
				httpServletResponse.sendRedirect("/");
				return null;
			}
		}
		// Open a new tab if it doesn't exist.
		Tab tab = new Tab(name, "/concept/" + name, true);
		if (!userSession.getOpenTabs().contains(tab))
			userSession.addTab(tab);
		else
			userSession.setActive(name);
		model.addAttribute("tabs", userSession.getOpenTabs());
		Concept concept = conceptService.findByConceptName(name);
		model.addAttribute("conceptName", name);
		// if user is a teacher get all answers and return the teacher template
		User user;
		if (req.isUserInRole("ROLE_TEACHER")) {
			Page<Answer> markedAnswers = answerService.findByMarkedAndConcept(true, concept, page);
			Page<Answer> unmarkedAnswers = answerService.findByMarkedAndConcept(false, concept, page);
			String url = concept.getURL();
			model.addAttribute("conceptURL", url);
			model.addAttribute("markedAnswers", markedAnswers);
			model.addAttribute("unmarkedAnswers", unmarkedAnswers);
			return "teacher";
		} else {
			user = userSession.getLoggedUser();
			Page<Question> markedQuestions = questionService.findByMarkedAndAnswer_ConceptAndUser(true, concept, user,
					page);
			Page<Question> unmarkedQuestions = questionService.findByMarkedAndAnswer_ConceptAndUser(false, concept,
					user, page);
			model.addAttribute("diagramInfo", chapterService.generateDiagramInfo(user));
			model.addAttribute("markedQuestions", markedQuestions);
			model.addAttribute("unmarkedQuestions", unmarkedQuestions);
			addQuestionInfoToModel(model, concept);
			return "concept";
		}
	}

	private void addQuestionInfoToModel(Model model, Concept concept) {
		Question question = questionGenerator.generateQuestion(concept);
		String answerText = question.getAnswer().getAnswerText();
		Justification justification = question.getJustification();
		if (answerText != null)
			model.addAttribute("answerText", answerText);
		if (justification != null)
			model.addAttribute("justificationText", justification.getJustificationText());
		model.addAttribute("questionText", question.getQuestionText());
		model.addAttribute("openQuestion", !question.isYesNoQuestion());
		model.addAttribute("questionType", question.getType());
	}

	@PostMapping("/mark/{id}")
	public String markAnswer(Model model, @PathVariable Long id,
			@RequestParam(value = "correctAnswer", required = false) String cAnswer,
			@RequestParam(value = "incorrectAnswer", required = false) String iAnswer,
			HttpServletResponse httpServletResponse) throws IOException {
		Answer ans = answerService.getOne(id);
		ans.setMarked(true);
		if (cAnswer != null && iAnswer == null) {
			ans.setCorrect(true);
			answerService.save(ans);
		} else if (cAnswer == null && iAnswer != null) {
			ans.setCorrect(false);
			answerService.save(ans);
		} else {
			// Error
		}
		httpServletResponse.sendRedirect("/");
		return null;
	}

	@RequestMapping("/delete/{conceptName}/{id}")
	public String deleteAnswer(Model model, @PathVariable String conceptName, @PathVariable Long id,
			HttpServletResponse httpServletResponse) throws IOException {
		answerService.deleteById(id);
		httpServletResponse.sendRedirect("/concept/" + conceptName);
		return null;
	}

	@PostMapping("/modifyAnswer/{conceptName}/{id}")
	public String addModifiedAnswer(Model model, @PathVariable String conceptName, @PathVariable Long id,
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
				ans.getJustifications().clear();
			} else {
				newJ = new Justification(justificationText, true, userSession.getLoggedUser());
				newJ.setValid(jValid.equals("yes"));
				if (jValid.equals("yes"))
					newJ.setError(error);
				ans.addJustification(newJ);

			}
		}
		answerService.save(ans);
		if (newJ != null) {
			newJ.setAnswer(ans);
			justificationService.save(newJ);
		}
		httpServletResponse.sendRedirect("/concept/" + conceptName);
		return null;
	}

	@RequestMapping("/addAnswer/{conceptName}")
	public String addAnswer(Model model, @PathVariable String conceptName, @RequestParam String questionText,
			@RequestParam String justificationText, @RequestParam String answerText,
			@RequestParam(value = "invalidJustification", required = false) String iJustification,
			@RequestParam(value = "validJustification", required = false) String vJustification,
			@RequestParam(value = "correctAnswer", required = false) String cAnswer,
			@RequestParam(value = "incorrectAnswer", required = false) String iAnswer,
			HttpServletResponse httpServletResponse) throws IOException {
		Answer ans = new Answer(answerText, true, userSession.getLoggedUser(),
				conceptService.findByConceptName(conceptName));
		if (cAnswer != null && iAnswer == null) {
			ans.setCorrect(true);
		} else if (cAnswer == null && iAnswer != null) {
			ans.setCorrect(false);
		}
		if (justificationText != "") {
			boolean valid = false;
			if (vJustification != null && iJustification == null) {
				valid = true;
			} else if (vJustification == null && iJustification != null) {
				valid = false;
			}
			Justification just = new Justification(justificationText, true, userSession.getLoggedUser());
			just.setValid(valid);
			ans.addJustification(just);
		}
		Concept con = conceptService.findByConceptName(conceptName);
		con.getAnswers().add(ans);
		conceptService.save(con);
		httpServletResponse.sendRedirect("/concept/" + conceptName);
		return null;
	}

	@PostMapping("/saveAnswer/{conceptName}")
	public String saveAnswer(Model model, @PathVariable String conceptName, HttpServletResponse httpServletResponse,
			@RequestParam String questionText, @RequestParam String questionType,
			@RequestParam(required = false) String answerText, @RequestParam(required = false) String answerOption,
			@RequestParam(required = false) String answerQuestionText,
			@RequestParam(required = false) String justificationQuestionText) throws IOException {
		boolean open = answerText != null;
		String answerFinalText = open ? answerText : answerOption;
		int parsedInt = Integer.parseInt(questionType);
		questionMarker.saveQuestion(conceptService.findByConceptName(conceptName), answerFinalText, questionText,
				parsedInt, answerQuestionText, justificationQuestionText);
		httpServletResponse.sendRedirect("/concept/" + conceptName);
		return null;
	}

	@PostMapping("/saveURL")
	public void saveURL(Model model, @RequestParam String conceptName, @RequestParam String url,
			HttpServletResponse httpServletResponse) throws IOException {
		conceptService.saveURL(conceptName, url);
		httpServletResponse.sendRedirect("/concept/" + conceptName);
	}

	@RequestMapping("/deleteJust/{conceptName}/{id}")
	public void deleteJustification(Model model, @PathVariable String conceptName, @PathVariable long id,
			HttpServletResponse httpServletResponse) throws IOException {
		justificationService.deleteById(id);
		httpServletResponse.sendRedirect("/concept/" + conceptName);
	}

	@RequestMapping("/modifyJust/{conceptName}/{id}")
	public void modifyJustification(Model model, @PathVariable String conceptName, @PathVariable long id,
			@RequestParam String jText, @RequestParam String valid, @RequestParam(required = false) String error,
			HttpServletResponse httpServletResponse) throws IOException {
		Justification j = justificationService.findById(id);
		j.setJustificationText(jText);
		j.setValid(valid.equals("yes"));
		if (error!=null)
			j.setError(error);
		httpServletResponse.sendRedirect("/concept/" + conceptName);
	}
}
