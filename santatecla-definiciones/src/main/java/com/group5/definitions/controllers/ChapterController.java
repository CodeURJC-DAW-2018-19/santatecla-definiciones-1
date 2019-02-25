package com.group5.definitions.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group5.definitions.images.Image;
import com.group5.definitions.model.Chapter;
import com.group5.definitions.model.Concept;
import com.group5.definitions.model.User;
import com.group5.definitions.services.ChapterService;
import com.group5.definitions.services.ConceptService;
import com.group5.definitions.services.UserService;
import com.group5.definitions.usersession.UserSessionService;

@Controller
public class ChapterController {

	@Autowired
	private ChapterService chapterService;
	private final int DEFAULT_SIZE = 10;
	@Autowired
	private ConceptService conceptService;

	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	private AtomicInteger imageId = new AtomicInteger();
	private Map<Integer, Image> images = new ConcurrentHashMap<>();

	@Autowired
	private UserSessionService userSession;

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() throws IOException {
		if (!Files.exists(FILES_FOLDER)) {
			Files.createDirectories(FILES_FOLDER);
		}
	}

	@ModelAttribute
	public void addUserToModel(Model model) {
		userSession.addUserToModel(model);
	}

	@RequestMapping("")
	public String loadHome(Model model, @RequestParam(name = "close", required = false) String closeTab,
			HttpServletRequest req) {
		addToModelHome(model, closeTab, req);
		return "home";
	}
	
	private void addToModelHome(Model model, String closeTab, HttpServletRequest req) {
		if (closeTab != null) {
			userSession.removeTab(closeTab);
		}
		userSession.setActive("inicio");
		model.addAttribute("tabs", userSession.getOpenTabs());
		model.addAttribute("teacher", req.isUserInRole("ROLE_TEACHER"));
		if (req.isUserInRole("ROLE_TEACHER")) {
			model.addAttribute("diagramInfo", chapterService.generateDiagramInfo());
		} else {
			model.addAttribute("diagramInfo", chapterService.generateDiagramInfo(userSession.getLoggedUser()));
		}
		model.addAttribute("images", images.values());
	}
	@RequestMapping("/loadChapters")
	public String getChapters(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE, sort = { "chapterName" }) Pageable page) {
		Page<Chapter> chapters = chapterService.findAll(page);
		model.addAttribute("chapters", chapters);
		model.addAttribute("teacher", req.isUserInRole("ROLE_TEACHER"));
		return "chapterInfo";
	}
	
	
	@RequestMapping("/loadConcepts")
	public String getConcepts(Model model, HttpServletRequest req,
			@PageableDefault(size = DEFAULT_SIZE, sort = { "conceptName" }) Pageable page, 
			@RequestParam("chapterId") String chapterId){
		Page<Concept> concepts = conceptService.findByChapter_id(Long.parseLong(chapterId), page);
		model.addAttribute("concepts", concepts);
		model.addAttribute("max", concepts.getTotalPages());
		model.addAttribute("chapterId", chapterId);
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
		addToModelHome(model, null, req);
		return "home";
	}

	@RequestMapping("/deleteChapter/{id}")
	public String deleteChapter(Model model, @PathVariable Long id, HttpServletRequest req) {
		chapterService.deleteById(id);
		addToModelHome(model, null, req);
		return "home";
	}

	@RequestMapping("/addConcept/{chapterName}")
	public String addConcept(Model model,HttpServletRequest req, 
	@PathVariable String chapterName, 
	@RequestParam String conceptName) {
		Chapter chap = chapterService.findByChapterName(chapterName);
		Concept con = new Concept(conceptName, chap);
		chap.getConcepts().add(con);
		conceptService.save(con);
		chapterService.save(chap);
		addToModelHome(model, null, req);
		return "home";
	}

	@RequestMapping("/deleteConcept/{id}")
	public String deleteConcept(Model model, @PathVariable Long id, HttpServletRequest req) {
		Concept c = conceptService.findById(id);
		conceptService.deleteById(id);
		addToModelHome(model, null, req);
		return "home";
	}

	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, @RequestParam("conceptName") String conceptName, HttpServletResponse httpServletResponse,
			@RequestParam("file") MultipartFile file) {

		int id = imageId.getAndIncrement();

		String fileName = "image-" + conceptName + ".jpg";

		if (!file.isEmpty()) {
			try {

				File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
				file.transferTo(uploadedFile);

				images.put(id, new Image(id));
				httpServletResponse.sendRedirect("/");
				return null;

			} catch (Exception e) {

				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

				return "error";
			}
		} else {

			model.addAttribute("error", "The file is empty");

			return "error";
		}
	}

	@RequestMapping("/image/{conceptName}")
	public void handleFileDownload(@PathVariable String conceptName, HttpServletResponse res)
			throws FileNotFoundException, IOException {

		String fileName = "image-" + conceptName + ".jpg";

		Path image = FILES_FOLDER.resolve(fileName);

		if (Files.exists(image)) {

		} else {
			image = FILES_FOLDER.resolve("imagePlaceholder.png");
		}
		res.setContentType("image/jpeg");
		res.setContentLength((int) image.toFile().length());
		FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());
	}

	@RequestMapping("/register")
	public String register(Model model) {
		return "register";
	}
	
	@PostMapping("/newUser")
	public String newUser(Model model, HttpServletRequest req, @RequestParam String username, @RequestParam String password) {
		userService.save(new User(username, password, "ROLE_STUDENT"));
		addToModelHome(model, null, req);
		return "loginPage";
	}
}
