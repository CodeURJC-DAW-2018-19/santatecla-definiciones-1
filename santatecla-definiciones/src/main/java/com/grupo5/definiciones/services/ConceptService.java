package com.grupo5.definiciones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.repositories.ConceptRepository;

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
	
	
}
