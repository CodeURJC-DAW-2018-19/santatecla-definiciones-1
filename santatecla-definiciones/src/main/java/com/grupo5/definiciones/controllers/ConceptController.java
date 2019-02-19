package com.grupo5.definiciones.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.services.AnswerService;
import com.grupo5.definiciones.services.ConceptService;
import com.grupo5.definiciones.usersession.Tab;
import com.grupo5.definiciones.usersession.UserSessionService;

@Controller
public class ConceptController {

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
	public String conceptPage(Model model, HttpServletRequest req, @PathVariable String name, @RequestParam(name="close", required=false) String closeTab) {
		if(closeTab!=null) {
			userSession.removeTab(closeTab);
			if (name.equals(closeTab)) {
				userSession.setActive("inicio");
				model.addAttribute("tabs", userSession.getOpenTabs());
				return "home";
			}
		}
		Concept concept = conceptService.findByConceptName(name);
		if (concept==null)
			return null;
		List<Answer> answers = concept.getAnswers();
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
		model.addAttribute("noMarkedAnswers", noMarkedAnswers);
		model.addAttribute("noUnmarkedAnswers", noUnmarkedAnswers);
		Tab tab = new Tab(name, "/concept/" + name, true);
		if (!userSession.getOpenTabs().contains(tab))
			userSession.addTab(tab);
		else
			userSession.setActive(name);
		model.addAttribute("tabs", userSession.getOpenTabs());
		if (req.isUserInRole("ROLE_DOCENTE")) {
			return "teacher";
		}
		return "concept";
	}
	
	@RequestMapping("/mark/{id}")
	public String markAnswer (Model model, @PathVariable Long id, @RequestParam(value = "correctAnswer", required = false) String cAnswer, @RequestParam(value = "incorrectAnswer", required = false) String iAnswer) {
		Answer ans = answerService.getOne(id);
		ans.setMarked(true);
		if(cAnswer != null && iAnswer == null) {
			ans.setCorrect(true);
			answerService.save(ans);
		} else if (cAnswer == null && iAnswer != null) {
			ans.setCorrect(false);
			answerService.save(ans);
		} else {
			//Error porque no podemos marcar ambas
		}
		return "home";
	}
	
	@Transactional
	@RequestMapping("/delete/{id}")
	public String deleteAnswer (Model model, @PathVariable Long id) {
		answerService.deleteById(id);
		return "home";
	}
	
}
