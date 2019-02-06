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
		Chapter chapter = new Chapter("Tema 1: Desarrollo web servidor");
		chapterRepository.save(chapter);
		Concept c1 = new Concept("Spring");
		Concept c2 = new Concept("APIs REST");
		
		c1.setChapter(chapter);
		c2.setChapter(chapter);
		
		conceptRepository.save(c1);
		conceptRepository.save(c2);
	}
}
