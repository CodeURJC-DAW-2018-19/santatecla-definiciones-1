package com.group5.definitions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.definitions.model.Concept;
import com.group5.definitions.repositories.ConceptRepository;

@Service
public class ConceptService {
	
	@Autowired
	private ConceptRepository conceptRepository;

	public Concept findByConceptName(String name) {
		return conceptRepository.findByConceptName(name);
	}
	
	public void save(Concept con) {
		conceptRepository.save(con);
	}
	
	public void deleteById(Long id) {
		conceptRepository.deleteById(id);
	}

	public Concept findById(Long id) {
		return conceptRepository.findById(id).get();
	}
	
	
}
