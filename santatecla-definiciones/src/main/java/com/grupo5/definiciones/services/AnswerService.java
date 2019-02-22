package com.grupo5.definiciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo5.definiciones.model.Answer;
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

	public List<Answer> findByUser(User user) {
		return answerRepository.findByUser(user);
	}
}
