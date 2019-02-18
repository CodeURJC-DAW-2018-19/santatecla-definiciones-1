package com.grupo5.definiciones.controllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.repositories.ChapterRepository;
import com.grupo5.definiciones.usersession.UserSessionInfoComponent;
import com.grupo5.definiciones.usersession.UserSessionService;

@Controller
public class ChapterController {

	@Autowired
	private ChapterRepository chapterRepository;
	private final int DEFAULT_SIZE = 10;

	@Autowired
	private UserSessionService userSession;

	@PostConstruct
	public void init() {
		/* test code 
		for (int i = 0; i < 20; i++) {
			Chapter chapter = new Chapter("Tema " + (4 + i));
			chapterRepository.save(chapter);
		}
		*/
	}

	@RequestMapping("/")
	public String loadHome(Model model) {
		userSession.setActive("inicio");
		model.addAttribute("tabs", userSession.getOpenTabs());
		return "home";
	}

	@RequestMapping("/loadChapters")
	public String getChapters(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE, sort = { "chapterName" }) Pageable page) {
		Page<Chapter> chapters = chapterRepository.findAll(page);
		model.addAttribute("empty", chapters.isEmpty());
		model.addAttribute("chapters", chapters);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		return "chapterInfo";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "loginPage";
	}

}
