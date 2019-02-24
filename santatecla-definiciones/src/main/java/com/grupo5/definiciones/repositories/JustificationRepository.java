package com.grupo5.definiciones.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Justification;

public interface JustificationRepository extends JpaRepository<Justification, Long> {

	Page<Justification> findByAnswer(Answer answer, Pageable page);

	long countByAnswer(Answer answer);

	Justification findByJustificationText(String justificationText);
	
}
