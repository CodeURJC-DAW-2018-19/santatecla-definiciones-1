package com.grupo5.definiciones.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.repositories.ConceptRepository;
import com.grupo5.definiciones.usersession.Tab;
import com.grupo5.definiciones.usersession.UserSessionInfoComponent;

@Controller
public class ConceptController {

	@Autowired
	private ConceptRepository conceptRepository;

	@Autowired
	private UserSessionInfoComponent userSession;

	@RequestMapping("/concept/{name}")
	public String conceptPage(Model model, HttpServletRequest req, @PathVariable String name) {
		Concept concept = conceptRepository.findByConceptName(name);
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
}
