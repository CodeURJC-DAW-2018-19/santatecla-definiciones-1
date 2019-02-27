package com.group5.definitions.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.definitions.model.Answer;
import com.group5.definitions.model.Concept;
import com.group5.definitions.model.User;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	public Page<Answer> findByConcept(Concept concept, Pageable page);

	public Page<Answer> findByConceptAndUser(Concept concept, User user, Pageable page);

	public long countByMarkedAndCorrect(boolean marked, boolean correct);

	public Page<Answer> findByMarkedAndCorrect(boolean marked, boolean correct, Pageable page);

	public Answer findByAnswerText(String answerText);

	public Page<Answer> findByMarkedAndConcept(boolean marked, Concept concept, Pageable page);

}
