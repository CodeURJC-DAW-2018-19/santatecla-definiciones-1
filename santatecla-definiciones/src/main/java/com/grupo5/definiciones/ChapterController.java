package com.grupo5.definiciones;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.repositories.ChapterRepository;

@Controller
public class ChapterController {

	@Autowired
	private ChapterRepository chapterRepository;

	@PostConstruct
	public void init() {
		// Init placeholder data
		Chapter chapter1 = new Chapter("Tema 1: Desarrollo web servidor");
		Chapter chapter2 = new Chapter("Tema 2: Despliegue de webs");
		Chapter chapter3 = new Chapter("Tema 3: Desarrollo web de cliente avanzado: SPA");
		Concept c11 = new Concept("Spring");
		Concept c12 = new Concept("APIs REST");
		chapter1.getConcepts().add(c11);
		chapter1.getConcepts().add(c12);
		Concept c21 = new Concept("Virtualización y Cloud Computing");
		Concept c22 = new Concept("Docker");
		chapter2.getConcepts().add(c21);
		chapter2.getConcepts().add(c22);
		Concept c31 = new Concept("Angular: Typescript y herramientas");
		Concept c32 = new Concept("Componentes de Angular");
		chapter3.getConcepts().add(c31);
		chapter3.getConcepts().add(c32);
		chapterRepository.save(chapter1);
		chapterRepository.save(chapter2);
		chapterRepository.save(chapter3);
	}

	@RequestMapping("/")
	public String getChaptersAndConcepts(Model model) {
		//Needed so it fetchs the concepts to avoid lazy initialization issues
		List<Chapter> chapters = chapterRepository.findChaptersOrderedByName();
		model.addAttribute("empty", chapters.isEmpty());
		model.addAttribute("chapters", chapters);
		return "home";
	}
	
	@RequestMapping("/concept")
	public String conceptPage(Model model,HttpServletRequest req) {
		//Test for user management only
		if(req.isUserInRole("DOCENTE")) {
			return "teacher";
		}
		return "concept";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	
	
}
