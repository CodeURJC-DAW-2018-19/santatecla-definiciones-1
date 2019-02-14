package com.grupo5.definiciones;

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

@Controller
public class ConceptController {
	
	@Autowired
	private ConceptRepository conceptRepository;
	
	@RequestMapping("/concept/{id}")
	public String conceptPage(Model model, HttpServletRequest req, @PathVariable long id) {
		Concept concept = conceptRepository.findById(id).get();
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
				}
				else {
					noUnmarkedAnswers = false;
				}
			}
		}
		model.addAttribute("noMarkedAnswers", noMarkedAnswers);
		model.addAttribute("noUnmarkedAnswers", noUnmarkedAnswers);
		if(req.isUserInRole("ROLE_DOCENTE")) {
			return "teacher";
		}
		return "concept";
	}
}
