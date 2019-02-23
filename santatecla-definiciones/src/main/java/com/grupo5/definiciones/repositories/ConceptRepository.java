package com.grupo5.definiciones.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;

public interface ConceptRepository extends JpaRepository<Concept, Long>{
	
	Concept	findByConceptName(String name);
	
	Page<Concept> findConceptByChapter(Chapter chapter, Pageable page);
	
}
