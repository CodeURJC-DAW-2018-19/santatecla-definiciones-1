package com.grupo5.definiciones;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo5.definiciones.model.User;
import com.grupo5.definiciones.repositories.UserRepository;

@Component
public class DataBaseUsersLoader {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	private void initDatabase() {
		
		userRepository.save(new User("user","pass","ESTUDIANTE"));
		userRepository.save(new User("admin","adminpass","ESTUDIANTE","DOCENTE"));
		
	}

}
