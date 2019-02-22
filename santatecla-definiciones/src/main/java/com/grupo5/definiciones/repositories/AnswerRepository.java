package com.grupo5.definiciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.User;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	List<Answer> findByUser(User user);

}
