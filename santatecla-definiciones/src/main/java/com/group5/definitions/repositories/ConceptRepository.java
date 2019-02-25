package com.group5.definitions.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;

public interface ConceptRepository extends JpaRepository<Concept, Long>{
	
	Concept	findByConceptName(String name);
	
	//Page<Concept> findConceptByChapter(Chapter chapter, Pageable page);

	Page<Concept> findByChapterConcept_id(long chapterId, Pageable page);
	
}
