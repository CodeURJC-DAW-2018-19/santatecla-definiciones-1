package com.group5.definitions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.group5.definitions.model.Concept;
import com.group5.definitions.model.Question;
import com.group5.definitions.model.User;
import com.group5.definitions.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public void save(Question question) {
		questionRepository.save(question);
	}

	public Page<Question> findByMarkedAndAnswer_Concept(boolean marked, Concept concept, Pageable page) {
		return questionRepository.findByMarkedAndAnswer_Concept(marked, concept, page);
	}

	public Page<Question> findByMarkedAndAnswer_ConceptAndUser(boolean marked, Concept concept, User user, Pageable page) {
		return questionRepository.findByMarkedAndAnswer_ConceptAndUser(marked, concept, user, page);

	}

}
