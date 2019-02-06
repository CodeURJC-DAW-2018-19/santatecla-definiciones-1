package com.grupo5.definiciones;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;

@Controller
public class ChapterController {
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	@Autowired
	private ConceptRepository conceptRepository;
	
	@PostConstruct
	public void init() {
		//Init placeholder data
		Chapter chapter1 = new Chapter("Tema 1: Desarrollo web servidor");
		Chapter chapter2 = new Chapter("Tema 2: Despliegue de webs");
		Chapter chapter3 = new Chapter("Tema 3: Desarrollo web de cliente avanzado: SPA");
		Concept c11 = new Concept("Spring");
		Concept c12 = new Concept("APIs REST");
		c11.setChapter(chapter1);
		c12.setChapter(chapter1);
		Concept c21 = new Concept("Virtualización y Cloud Computing");
		Concept c22 = new Concept("Docker");
		c21.setChapter(chapter2);
		c22.setChapter(chapter2);
		Concept c31 = new Concept("Angular: Typescript y herramientas");
		Concept c32 = new Concept("Componentes de Angular");
		c31.setChapter(chapter3);
		c32.setChapter(chapter3);
		chapterRepository.save(chapter1);
		chapterRepository.save(chapter2);
		chapterRepository.save(chapter3);
		conceptRepository.save(c11);
		conceptRepository.save(c12);
		conceptRepository.save(c21);
		conceptRepository.save(c22);
		conceptRepository.save(c31);
		conceptRepository.save(c32);
	}
}
