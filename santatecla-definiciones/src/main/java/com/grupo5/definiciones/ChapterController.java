package com.grupo5.definiciones;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.repositories.ChapterRepository;

@Controller
public class ChapterController {

	@Autowired
	private ChapterRepository chapterRepository;

	@PostConstruct
	public void init() {
		/* test code
		for(int i = 0;i < 100; i++) {
			Chapter chapter = new Chapter("Tema "+4+i);
			chapterRepository.save(chapter1);
		}*/
	}

	@RequestMapping("/")
	public String getChaptersAndConcepts(Model model, HttpServletRequest req, Pageable page) {
		//Needed so it fetchs the concepts to avoid lazy initialization issues
		int elemsPerPage = 1;
		Page<Chapter> chapters = chapterRepository.findAll(PageRequest.of(0, elemsPerPage, Sort.by(Direction.ASC, "chapterName")));
		
		model.addAttribute("empty", chapters.isEmpty());
		model.addAttribute("chapters", chapters);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		
		model.addAttribute("showMore", !chapters.isLast());
		model.addAttribute("nextPage", elemsPerPage);
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "loginPage";
	}
	
}

