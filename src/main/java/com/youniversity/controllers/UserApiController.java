package com.youniversity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youniversity.models.User;
import com.youniversity.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

	private UserRepository userRepository;
	private PasswordEncoder encoder;

	public UserApiController(UserRepository userRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	@GetMapping
	public User getUser(Authentication auth) {
		User user = (User) auth.getPrincipal();
		String username = user.getUsername();

		return userRepository.findByUsername(username);
	}

	@PutMapping
	public User updateUser(Authentication auth, @RequestBody User user) {

		User loggedInUser = (User) auth.getPrincipal();
		user.setId(loggedInUser.getId());
		
		if(user.getPassword() == null) {
			user.setPassword(loggedInUser.getPassword());
		}else {
			String encryptedPassword = encoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
		}

		return userRepository.save(user);
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		String password = user.getPassword();
		String encryptedPassword = encoder.encode(password);
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		return user;
	}
}
