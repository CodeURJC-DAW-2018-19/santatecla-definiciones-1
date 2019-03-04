package com.group5.definitions.restcontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Chapter;
import com.group5.definitions.model.Concept;
import com.group5.definitions.services.ChapterService;

@RestController
@RequestMapping("/api")
public class RestChapterController {
	
	@Autowired
	private ChapterService chapterService;
	private final int DEFAULT_SIZE = 10;
	
	interface PageChapter extends Chapter.Basic, Page {}
	@JsonView(PageChapter.class)
	@GetMapping("/chapters")
	public Page<Chapter> getChapters(@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		Page<Chapter> chapters = chapterService.findAll(page);
		return chapters;
	}
	
	interface ChapterConcept extends Chapter.Basic, Chapter.Concepts, Concept.Basic {}
	@JsonView(ChapterConcept.class)
	@GetMapping("/chapters/{id}")
	public ResponseEntity<Chapter> getChaptersById(@PathVariable long id) {
		Chapter chapter = chapterService.findById(id);
		if (chapter!=null)
			return new ResponseEntity<>(chapter, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
