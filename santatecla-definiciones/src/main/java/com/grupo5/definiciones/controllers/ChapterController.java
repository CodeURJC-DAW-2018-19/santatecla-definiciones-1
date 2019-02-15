package com.grupo5.definiciones.controllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.repositories.ChapterRepository;
import com.grupo5.definiciones.usersession.UserSessionInfoComponent;

@Controller
public class ChapterController {

	@Autowired
	private ChapterRepository chapterRepository;

	@Autowired
	private UserSessionInfoComponent userSession;

	@PostConstruct
	public void init() {
		/*
		 * test code for(int i = 0;i < 100; i++) { Chapter chapter = new
		 * Chapter("Tema "+4+i); chapterRepository.save(chapter1); }
		 */
	}

	@RequestMapping("/")
	public String getChaptersAndConcepts(Model model, HttpServletRequest req, Pageable page) {
		// Needed so it fetchs the concepts to avoid lazy initialization issues
		Page<Chapter> chapters = chapterRepository.findAll(page);

		// model.addAttribute("anuncios", chapterRepository.findAll( page));

		model.addAttribute("empty", chapters.isEmpty());
		model.addAttribute("chapters", chapters);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));

		model.addAttribute("showNext", !chapters.isLast());
		model.addAttribute("showPrev", !chapters.isFirst());
		model.addAttribute("numPage", chapters.getNumber());
		model.addAttribute("nextPage", chapters.getNumber() + 1);
		model.addAttribute("prevPage", chapters.getNumber() - 1);
		userSession.setActive("inicio");
		model.addAttribute("tabs", userSession.getOpenTabs());
		return "home";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "loginPage";
	}

}
