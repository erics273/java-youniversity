package com.youniversity.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youniversity.models.Student;
import com.youniversity.repositories.StudentRepository;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepo;
	
	@GetMapping
	public List<Student> getStudents(){
		return studentRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Student getStudent(@PathVariable Long id){
		return studentRepo.findOne(id);
	}

}
