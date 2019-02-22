package com.grupo5.definiciones.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.model.Justification;
import com.grupo5.definiciones.model.User;
import com.grupo5.definiciones.services.AnswerService;
import com.grupo5.definiciones.services.ChapterService;
import com.grupo5.definiciones.services.ConceptService;
import com.grupo5.definiciones.usersession.Tab;
import com.grupo5.definiciones.usersession.UserSessionService;

@Controller
public class ConceptController {

	@Autowired
	private ChapterService chapterService;

	@Autowired
	private ConceptService conceptService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private UserSessionService userSession;

	@ModelAttribute
	public void addUserToModel(Model model) {
		userSession.addUserToModel(model);
	}

	@RequestMapping("/concept/{name}")
	public String conceptPage(Model model, HttpServletRequest req, @PathVariable String name,
			@RequestParam(name = "close", required = false) String closeTab, HttpServletResponse httpServletResponse)
			throws IOException {
		// If close tab button was pressed, remove the tab
		if (closeTab != null) {
			userSession.removeTab(closeTab);
			// If user is in the tab that is being closed, redirect to home page
			if (name.equals(closeTab)) {
				/*
				 * userSession.setActive("inicio"); 
				 * model.addAttribute("tabs", userSession.getOpenTabs()); 
				 * return "home";
				 */
				httpServletResponse.sendRedirect("/");
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
		List<Answer> answers;
		// if user is a teacher get all answers and return the teacher template
		User user;
		if (req.isUserInRole("ROLE_DOCENTE")) {
			answers = concept.getAnswers();
			model.addAttribute("diagramInfo", chapterService.generateDiagramInfo());
		} else {
			user = userSession.getLoggedUser();
			answers = answerService.findByUser(user);
			model.addAttribute("diagramInfo", chapterService.generateDiagramInfo(user));
		}
		model.addAttribute("answers", answers);
		boolean noMarkedAnswers = true;
		boolean noUnmarkedAnswers = true;
		if (!answers.isEmpty()) {
			for (Answer a : answers) {
				if (!noMarkedAnswers && !noUnmarkedAnswers)
					break;
				if (a.isMarked()) {
					noMarkedAnswers = false;
				} else {
					noUnmarkedAnswers = false;
				}
			}
		}
		if (req.isUserInRole("ROLE_DOCENTE")) {
			return "teacher";
		}
		model.addAttribute("noMarkedAnswers", noMarkedAnswers);
		model.addAttribute("noUnmarkedAnswers", noUnmarkedAnswers);
		return "concept";
	}

	@RequestMapping("/mark/{id}")
	public String markAnswer(Model model, @PathVariable Long id,
			@RequestParam(value = "correctAnswer", required = false) String cAnswer,
			@RequestParam(value = "incorrectAnswer", required = false) String iAnswer) {
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
		return "home";
	}

	@Transactional
	@RequestMapping("/delete/{id}")
	public String deleteAnswer(Model model, @PathVariable Long id) {
		answerService.deleteById(id);
		return "home";
	}

	@RequestMapping("/modifyAnswer/{conceptName}/{id}")
	public String modifyAnswer(Model model, @PathVariable String conceptName, @PathVariable Long id) {
		Answer ans = answerService.getOne(id);
		model.addAttribute("answer", ans);
		model.addAttribute("conceptName", conceptName);
		return "modifyAnswer";
	}

	@RequestMapping("/addModifiedAnswer/{conceptName}/{id}")
	public String addModifiedAnswer(Model model, @PathVariable String conceptName, @PathVariable Long id,
			@RequestParam String justificationText, @RequestParam String answerText,
			@RequestParam(value = "invalidJustification", required = false) String iJustification,
			@RequestParam(value = "validJustification", required = false) String vJustification,
			@RequestParam(value = "correctAnswer", required = false) String cAnswer,
			@RequestParam(value = "incorrectAnswer", required = false) String iAnswer) {
		Answer ans = answerService.getOne(id);
		ans.setAnswerText(answerText);
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
			Justification just = new Justification(justificationText, valid);
			ans.setJustification(just);
		} else {
			ans.setJustification(null);
		}
		answerService.save(ans);
		return "home";
	}

	@RequestMapping("/addAnswer/{conceptName}")
	public String addAnswer(Model model, @PathVariable String conceptName, @RequestParam String questionText,
			@RequestParam String justificationText, @RequestParam String answerText,
			@RequestParam(value = "invalidJustification", required = false) String iJustification,
			@RequestParam(value = "validJustification", required = false) String vJustification,
			@RequestParam(value = "correctAnswer", required = false) String cAnswer,
			@RequestParam(value = "incorrectAnswer", required = false) String iAnswer) {
		Answer ans = new Answer(questionText, answerText, true, null);
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
			Justification just = new Justification(justificationText, valid);
			ans.setJustification(just);
		}
		Concept con = conceptService.findByConceptName(conceptName);
		con.getAnswers().add(ans);
		conceptService.save(con);
		return "home";
	}
  
	@RequestMapping("/saveAnswer/{conceptName}")
	public String saveAnswer (Model model, @PathVariable String conceptName, @RequestParam String questionText, @RequestParam String answerText) {
		Answer ans = new Answer(questionText,answerText,false);
		ans.setAnswerText(answerText);
		Concept con = conceptService.findByConceptName(conceptName);
		con.getAnswers().add(ans);
		answerService.save(ans);
		return "home";
	}
  
}
