package com.group5.definitions.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.group5.definitions.model.User;
import com.group5.definitions.services.UserService;
import com.group5.definitions.usersession.UserSessionService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSessionService userSession;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User addNewUser(@RequestBody User user) {
		// Assuming the password isn't encrypted in the Request Body
		// Ignores roles added in user Request Body in favor of being only a student
		User newUser = new User(user.getName(), user.getPassword(), "ROLE_STUDENT");
		userService.save(newUser);
		return newUser;
	}
	
	@GetMapping("/currentuser")
	public User getCurrentUser() {
		return userSession.getLoggedUser();
	}
}
