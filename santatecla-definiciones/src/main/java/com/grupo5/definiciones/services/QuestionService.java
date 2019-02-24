package com.grupo5.definiciones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.model.Question;
import com.grupo5.definiciones.model.User;
import com.grupo5.definiciones.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	public Page<Question> findByAnswer_Concept(Concept concept, Pageable page) {
		return questionRepository.findByAnswer_Concept(concept, page);
	}

	public Page<Question> findByAnswer_ConceptAndAnswer_User(Concept concept, User user, Pageable page) {
		return questionRepository.findByAnswer_ConceptAndAnswer_User(concept, user, page);
	}

}
