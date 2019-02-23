package com.grupo5.definiciones.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.model.User;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	public Page<Answer> findByConcept(Concept concept, Pageable page);

	public Page<Answer> findByConceptAndUser(Concept concept, User user, Pageable page);

}
