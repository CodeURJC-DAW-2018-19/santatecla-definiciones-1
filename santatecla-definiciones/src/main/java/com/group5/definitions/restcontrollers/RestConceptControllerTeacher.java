package com.group5.definitions.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Concept;
import com.group5.definitions.services.ConceptService;

@RestController
@RequestMapping("/api")
public class RestConceptControllerTeacher {

	@Autowired
	private ConceptService conceptService;
	
	@JsonView(Concept.Basic.class)
	@PutMapping("/concept/{id}")
	public ResponseEntity<Concept> updateConcept(@PathVariable long id, @RequestBody Concept concept) {
		Concept oldConcept = conceptService.findById(id);
		if (oldConcept == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		concept.setId(id);
		if (concept.getChapter()==null)
			concept.setChapter(oldConcept.getChapter());
		conceptService.save(concept);
		return new ResponseEntity<>(concept, HttpStatus.OK);
	}
}
