package com.grupo5.definiciones.controllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;

import com.grupo5.definiciones.services.ChapterService;

import com.grupo5.definiciones.usersession.UserSessionService;

@Controller
public class ChapterController {

	@Autowired
	private ChapterService chapterService;
	private final int DEFAULT_SIZE = 10;

	@Autowired
	private UserSessionService userSession;

	@PostConstruct
	public void init() {
		/* test code 
		for (int i = 0; i < 20; i++) {
			Chapter chapter = new Chapter("Tema " + (4 + i));
			chapterService.save(chapter);
		}
		*/
	}

	@ModelAttribute
	public void addUserToModel(Model model) {
		userSession.addUserToModel(model);
	}
	
	@RequestMapping("")
	public String loadHome(Model model, @RequestParam(name="close", required=false) String closeTab, HttpServletRequest req) {
		if(closeTab!=null) {
			userSession.removeTab(closeTab);
		}
		userSession.setActive("inicio");
		model.addAttribute("tabs", userSession.getOpenTabs());
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		return "home";
	}

	@RequestMapping("/loadChapters")
	public String getChapters(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE, sort = { "chapterName" }) Pageable page) {
		Page<Chapter> chapters = chapterService.findAll(page);
		model.addAttribute("empty", chapters.isEmpty());
		model.addAttribute("chapters", chapters);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		return "chapterInfo";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "loginPage";
	}
	
	@RequestMapping("/addChapter")
	public String addChapter(Model model, @RequestParam String chapterName, HttpServletRequest req) {
		Chapter chap = new Chapter(chapterName);
		chapterService.save(chap);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		return "home";
	}
	
	@RequestMapping("/deleteChapter/{id}")
	public String deleteChapter(Model model, @PathVariable Long id, HttpServletRequest req ) {
		chapterService.deleteById(id);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		return "home";
	}
	
	@RequestMapping("/addConcept/{chapterName}")
	public String addConcept(Model model,HttpServletRequest req, @PathVariable String chapterName, @RequestParam String conceptName) {
		Concept con = new Concept(conceptName);
		Chapter chap = chapterService.findByChapterName(conceptName);
		chap.getConcepts().add(con);
		chapterService.save(chap);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		return "home";
	}

}
