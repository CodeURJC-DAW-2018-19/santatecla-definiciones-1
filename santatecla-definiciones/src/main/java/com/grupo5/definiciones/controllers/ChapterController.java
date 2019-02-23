package com.grupo5.definiciones.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.grupo5.definiciones.images.Image;
import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.services.ChapterService;

import com.grupo5.definiciones.usersession.UserSessionService;

@Controller
public class ChapterController {

	@Autowired
	private ChapterService chapterService;
	private final int DEFAULT_SIZE = 10;
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	private AtomicInteger imageId = new AtomicInteger();
	private Map<Integer, Image> images = new ConcurrentHashMap<>();

	@Autowired
	private UserSessionService userSession;

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
	public String loadHome(Model model, @RequestParam(name="close", required=false) String closeTab, HttpServletRequest req) {
		if(closeTab!=null) {
			userSession.removeTab(closeTab);
		}
		userSession.setActive("inicio");
		model.addAttribute("tabs", userSession.getOpenTabs());
		model.addAttribute("docente", req.isUserInRole("ROLE_DOCENTE"));
		model.addAttribute("images", images.values());
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
	
	
	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, @RequestParam("imageTitle") String imageTitle,
			@RequestParam("file") MultipartFile file) {

		int id = imageId.getAndIncrement();
		
		String fileName = "image-" + id + ".jpg";

		if (!file.isEmpty()) {
			try {

				File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
				file.transferTo(uploadedFile);

				images.put(id, new Image(id, imageTitle));

				return "home";

			} catch (Exception e) {

				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

				return "error";
			}
		} else {
			
			model.addAttribute("error", "The file is empty");

			return "error";
		}
	}

	@RequestMapping("/image/{id}")
	public void handleFileDownload(@PathVariable String id, HttpServletResponse res)
			throws FileNotFoundException, IOException {

		String fileName = "image-" + id + ".jpg";
		
		Path image = FILES_FOLDER.resolve(fileName);

		if (Files.exists(image)) {
			res.setContentType("image/jpeg");
			res.setContentLength((int) image.toFile().length());
			FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());

		} else {
			res.sendError(404, "File" + fileName + "(" + image.toAbsolutePath() + ") does not exist");
		}
	}


}
