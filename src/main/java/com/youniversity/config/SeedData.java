package com.youniversity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.youniversity.models.User;
import com.youniversity.repositories.UserRepository;

@Configuration
public class SeedData {

	// Award award = awardRepository.save(new Award("Best Actor", "Academy Awards",
	// 2002));

	public SeedData(UserRepository userRepository, PasswordEncoder encoder) {

		userRepository.save(new User("admin", "user", "username", encoder.encode("password")));
	}
}
