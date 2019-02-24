package com.grupo5.definiciones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupo5.definiciones.model.Chapter;
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
	
	public Page<Concept> findConceptByChapter(Chapter chapter, Pageable page){
		return conceptRepository.findConceptByChapter(chapter, page);
	}//migth delete

	public Page<Concept> findByChapter_id(long chapterId, Pageable page) {
		return conceptRepository.findByChapter_id(chapterId, page);
	}
	
	
}
