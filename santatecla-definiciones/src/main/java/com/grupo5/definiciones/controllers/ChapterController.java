package com.grupo5.definiciones.controllers;

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
import com.grupo5.definiciones.services.ConceptService;
import com.grupo5.definiciones.usersession.UserSessionService;

@Controller
public class ChapterController {

	@Autowired
	private ChapterService chapterService;
	private final int DEFAULT_SIZE = 1;
	
	@Autowired
	private ConceptService conceptService;

	@Autowired
	private UserSessionService userSession;

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
		model.addAttribute("diagramInfo", chapterService.generateDiagramInfo());
		return "home";
	}

	@RequestMapping("/loadChapters")
	public String getChapters(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE, sort = { "chapterName" }) Pageable page) {
		Page<Chapter> chapters = chapterService.findAll(page);
		model.addAttribute("chapters", chapters);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		return "chapterInfo";
	}
	
	
	@RequestMapping("/loadConcepts")
	public String getConcepts(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE, sort = { "conceptName" }) Pageable page, 
			@RequestParam("chapterName") String chapterName){
		Page<Concept> concepts = conceptService.findConceptByChapter(chapterService.findByChapterName(chapterName), page);
		model.addAttribute("conceptsEmpty", concepts.isEmpty());
		model.addAttribute("conceptsPage", concepts);
		System.out.println(model);
		return "conceptInfo";
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
		Chapter chap = chapterService.findByChapterName(chapterName);
		Concept con = new Concept(conceptName, chap);
		chap.getConcepts().add(con);
		chapterService.save(chap);
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		return "home";
	}
	
	@RequestMapping("/deleteConcept/{id}")
	public String deleteConcept(Model model, @PathVariable Long id, HttpServletRequest req) {
		conceptService.deleteById(id);
		model.addAttribute("docente",req.isUserInRole("ROLE_DOCENTE"));
		return "home";
	}

}
