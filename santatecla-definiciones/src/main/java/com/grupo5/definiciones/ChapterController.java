package com.grupo5.definiciones;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		
		/* test code
		for(int i = 0;i < 100; i++) {
			Chapter chapter = new Chapter("Tema "+4+i);
			chapterRepository.save(chapter1);
		}*/
	}

	@RequestMapping("/")
	public String getChaptersAndConcepts(Model model, HttpServletRequest req, Pageable page) {
		//Needed so it fetchs the concepts to avoid lazy initialization issues
		Page<Chapter> chapters = chapterRepository.findAll(page);
		
		//model.addAttribute("anuncios", chapterRepository.findAll( page));
		
		model.addAttribute("empty", chapters.isEmpty());
		model.addAttribute("chapters", chapters);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		
		model.addAttribute("showNext", !chapters.isLast());
		model.addAttribute("showPrev", !chapters.isFirst());
		model.addAttribute("numPage", chapters.getNumber());
		model.addAttribute("nextPage", chapters.getNumber()+1);
		model.addAttribute("prevPage", chapters.getNumber()-1);
		
		return "home";
	}
	
	@RequestMapping("/concept")
	public String conceptPage(Model model,HttpServletRequest req) {
		//Test for user management only
		if(req.isUserInRole("ROLE_DOCENTE")) {
			return "teacher";
		}
		return "concept";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "loginPage";
	}
	
}

