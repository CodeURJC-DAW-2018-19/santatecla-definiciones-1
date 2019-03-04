package com.group5.definitions.restcontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Chapter;
import com.group5.definitions.model.Concept;
import com.group5.definitions.model.Justification;
import com.group5.definitions.services.ChapterService;
import com.group5.definitions.services.ConceptService;
import com.group5.definitions.services.JustificationService;

@RestController
@RequestMapping("/api")
public class RestChapterController {
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private ConceptService conceptService;
	
	@Autowired
	private JustificationService justificationService;
	
	private final int DEFAULT_SIZE = 10;
	
	@JsonView(Chapter.Basic.class)
	@GetMapping(value = {"", "/chapters"})
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
	
	@JsonView(ChapterConcept.class)
	@RequestMapping(value="/deleteChapter/chapter/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Chapter> deleteChapter(@PathVariable Long id){
		Chapter chapter = chapterService.findById(id);
		chapterService.deleteById(id);
		if(chapter != null) {
			return new ResponseEntity<>(chapter,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(Concept.Basic.class)
	@RequestMapping(value="/deleteConcept/concept/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Concept> deleteConcept(@PathVariable Long id){
		Concept concept = conceptService.findById(id);
		conceptService.deleteById(id);
		if(concept != null) {
			return new ResponseEntity<>(concept,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(Concept.Basic.class)
	@RequestMapping(value="/addConcept", method=RequestMethod.POST)
	public ResponseEntity<Concept> addConcept(@PathVariable Long id, @PathVariable String conceptName, 
			@PathVariable String chapterId){
		Chapter chapter = chapterService.findById(Long.parseLong(chapterId));
		Concept concept = new Concept(conceptName, chapter);
		chapter.getConcepts().add(concept);
		conceptService.save(concept);
		chapterService.save(chapter);
			return new ResponseEntity<>(concept,HttpStatus.OK);
	}
	
	@JsonView(Concept.Basic.class)
	@RequestMapping(value="/deleteJust/concept/{conceptId}/justification/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Justification> deleteJustification(@PathVariable String conceptId, @PathVariable long id){
	Justification justification = justificationService.findById(id);
	if (justification.getAnswer().getJustifications().size() > 1) {
		justificationService.deleteById(id);
		return new ResponseEntity<>(justification,HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
	
	
}
