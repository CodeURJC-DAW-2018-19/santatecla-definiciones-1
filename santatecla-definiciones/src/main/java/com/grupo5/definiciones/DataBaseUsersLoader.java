package com.grupo5.definiciones;

import java.util.ArrayList;
import java.util.List;

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
		
		List<String> role1 = new ArrayList<String>();
		role1.add("ROLE_USER");
		List<String> role2 = new ArrayList<String>();
		role2.add("ROLE_USER");
		role2.add("ROLE_ADMIN");
		
		userRepository.save(new User("user","pass",role1));
		userRepository.save(new User("admin","adminpass",role2));
		
	}

}
