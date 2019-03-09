package com.group5.definitions.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Chapter;
import com.group5.definitions.model.Concept;
import com.group5.definitions.services.ChapterService;
import com.group5.definitions.services.ConceptService;

@RestController
@RequestMapping("/api")
public class RestChapterController {

	@Autowired
	private ChapterService chapterService;

	@Autowired
	private ConceptService conceptService;

	private final int DEFAULT_SIZE = 10;

	
	//Chapter methods
	@JsonView(Chapter.Basic.class) //We could change to the interface ChapterConcept so it gets the corresponding chapters too
	@GetMapping(value = { "", "/chapters" })
	public Page<Chapter> getChapters(@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		Page<Chapter> chapters = chapterService.findAll(page);
		return chapters;
	}

	@JsonView(ChapterConcept.class)
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value= {"", "/chapters"} , method = RequestMethod.POST)
	public ResponseEntity<Chapter> addChapter(@RequestBody Chapter chapter){
		chapterService.save(chapter);
		return new ResponseEntity<>(chapter, HttpStatus.OK);
	}

	@JsonView(ChapterConcept.class)
	@RequestMapping(value = "/chapters/", method = RequestMethod.DELETE)
	public ResponseEntity<Chapter> deleteChapter(@RequestBody Chapter chapter) {
		chapterService.deleteById(chapter.getId());
		return new ResponseEntity<>(chapter, HttpStatus.OK);
	}

	
	//Concept methods
	interface ChapterConcept extends Chapter.Basic, Chapter.Concepts, Concept.Basic {}
	@JsonView(ChapterConcept.class)
	@GetMapping("/chapters/{id}/concepts")
	public Page<Concept> getConcepts(@PathVariable long id, @PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		Page<Concept> concepts = conceptService.findByChapter_Id(id, page);
		return concepts;
	}
	
	@JsonView(Concept.Basic.class)
	@RequestMapping(value = "/chapters/{id}/concepts/", method = RequestMethod.DELETE)
	public ResponseEntity<Concept> deleteConcept(@PathVariable Long id, @RequestBody Concept concept) {
		conceptService.deleteById(concept.getId());
		return new ResponseEntity<>(concept, HttpStatus.OK);
	}

	@JsonView(Concept.Basic.class)
	@RequestMapping(value = "/chapters/{id}/concepts", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Concept> addConcept(@PathVariable long id, @RequestBody Concept concept) {
		Chapter chapter = chapterService.findById(id);
		chapter.getConcepts().add(concept);
		concept.setChapter(chapter);
		conceptService.save(concept);
		chapterService.save(chapter);
		return new ResponseEntity<>(concept, HttpStatus.OK);
	}
}
