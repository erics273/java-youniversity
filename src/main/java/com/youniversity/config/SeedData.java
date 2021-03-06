package com.youniversity.config;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.youniversity.models.Major;
import com.youniversity.models.Student;
import com.youniversity.models.User;
import com.youniversity.repositories.MajorRepository;
import com.youniversity.repositories.StudentRepository;
import com.youniversity.repositories.UserRepository;

@Configuration
public class SeedData {
	
	public SeedData(UserRepository userRepository, StudentRepository studentRepository, MajorRepository  majorRepository, @Autowired
			PasswordEncoder encoder) {

		String encodedPassword = encoder.encode("password");
		
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@admin.com");
		user.setFirstName("Eric");
		user.setLastName("Schwartz");
		user.setPassword(encodedPassword);
		user.addRole("ADMIN");
		userRepository.save(user);
		
		User studentUser = new User();
		studentUser.setUsername("student");
		studentUser.setEmail("student@student.com");
		studentUser.setFirstName("Curtis");
		studentUser.setLastName("Shlack");
		studentUser.setPassword(encodedPassword);
		userRepository.save(studentUser);
		
		//major seed
		Major major = new Major("Computer Science", 1000);
		
		//student see
		Student student = new Student("Eric", "Schwartz",3.8, 1200, new Date(117, 8, 20));
		student.setMajor(major);
		studentRepository.save(student);
		
		
	}
}
