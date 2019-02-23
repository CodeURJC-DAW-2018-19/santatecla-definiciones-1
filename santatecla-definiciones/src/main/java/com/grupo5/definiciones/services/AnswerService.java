package com.grupo5.definiciones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.model.User;
import com.grupo5.definiciones.repositories.AnswerRepository;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;

	public Answer getOne(Long id) {
		return answerRepository.getOne(id);
	}

	public void save(Answer ans) {
		answerRepository.save(ans);
	}

	public void deleteById(Long id) {
		answerRepository.deleteById(id);
	}

	public Page<Answer> findByConcept(Concept concept, Pageable page) {
		return answerRepository.findByConcept(concept, page);
	}

	public Page<Answer> findByConceptAndUser(Concept concept, User user, Pageable page) {
		return answerRepository.findByConceptAndUser(concept, user, page);
	}
}
