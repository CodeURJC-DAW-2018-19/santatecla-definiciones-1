package com.grupo5.definiciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.User;

public interface ChapterRepository extends JpaRepository<Chapter, Long>{
	
	Chapter findByChapterName(String chapterName);
	
	long countByIdAndConcepts_Answers_Marked(long id, boolean marked);
	long countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_Correct(long id, boolean marked, boolean correct);

	long countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_User(long id, boolean b, User user);

	long countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_CorrectAndConcepts_Answers_User(long id, boolean b,
			boolean c, User user);
}
