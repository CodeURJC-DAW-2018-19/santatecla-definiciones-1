package com.grupo5.definiciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long>{
	
	Chapter findByChapterName(String chapterName);
	
	long countByIdAndConcepts_Answers_Marked(long id, boolean marked);
	long countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_Correct(long id, boolean marked, boolean correct);
}
