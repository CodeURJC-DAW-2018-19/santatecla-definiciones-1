package com.group5.definitions.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.definitions.model.Answer;
import com.group5.definitions.model.Justification;

public interface JustificationRepository extends JpaRepository<Justification, Long> {

	Page<Justification> findByAnswer(Answer answer, Pageable page);

	long countByAnswer(Answer answer);

	Justification findByJustificationText(String justificationText);

	long countByAnswer_Id(long answerId);

}
