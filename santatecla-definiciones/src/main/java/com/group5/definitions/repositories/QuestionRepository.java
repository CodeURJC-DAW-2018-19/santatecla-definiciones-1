package com.group5.definitions.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.definitions.model.Concept;
import com.group5.definitions.model.Question;
import com.group5.definitions.model.User;

public interface QuestionRepository extends JpaRepository<Question, Long>{

	Page<Question> findByMarkedAndAnswer_Concept(boolean marked, Concept concept, Pageable page);

	Page<Question> findByMarkedAndAnswer_ConceptAndUser(boolean marked, Concept concept, User user, Pageable page);

}
