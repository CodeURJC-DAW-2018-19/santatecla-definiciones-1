package com.group5.definitions.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group5.definitions.images.Image;

@Controller
public class ImageController {

	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	private AtomicInteger imageId = new AtomicInteger();
	private Map<Integer, Image> images = new ConcurrentHashMap<>();
	
	@PostConstruct
	public void init() throws IOException {
		if (!Files.exists(FILES_FOLDER)) {
			Files.createDirectories(FILES_FOLDER);
		}
	}
	
	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, @RequestParam("conceptId") String conceptId, HttpServletResponse httpServletResponse,
			@RequestParam("file") MultipartFile file) {

		int id = imageId.getAndIncrement();

		String fileName = "image-" + conceptId + ".jpg";

		if (!file.isEmpty()) {
			try {

				File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
				file.transferTo(uploadedFile);

				images.put(id, new Image(id));
				httpServletResponse.sendRedirect("/concept/"+ conceptId);
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

	@RequestMapping("/image/concept/{conceptId}")
	public void handleFileDownload(@PathVariable String conceptId, HttpServletResponse res)
			throws FileNotFoundException, IOException {

		String fileName = "image-" + conceptId + ".jpg";

		Path image = FILES_FOLDER.resolve(fileName);

		if (Files.exists(image)) {

		} else {
			image = FILES_FOLDER.resolve("imagePlaceholder.png");
		}
		res.setContentType("image/jpeg");
		res.setContentLength((int) image.toFile().length());
		FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());
	}
	
	public Collection<Image> getImageValues() {
		return images.values();
	}
}
