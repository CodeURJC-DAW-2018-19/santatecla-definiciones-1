package com.group5.definitions.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.definitions.services.ChapterService;
import com.group5.definitions.services.DiagramChapterInfo;

@RestController
@RequestMapping("/api")
public class DiagramRestController {

	@Autowired
	private ChapterService chapterService;
	private final int DEFAULT_SIZE = 10;
	
	@GetMapping("/diagramInfo")
	public Page<DiagramChapterInfo> getDiagramInfo(@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		return chapterService.generateDiagramInfoPage(page);
	}
}
