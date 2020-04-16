package com.saty.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saty.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	//define @PostConstruct to load student data....only once!
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		theStudents.add(new Student("Satyarth","Shukla"));
		theStudents.add(new Student("Ricky","Jaiswal"));
		theStudents.add(new Student("Ronny","Dsouza"));
	}
	
		//define endpoint for "/students" - get list of students
	
		@GetMapping("/students")
		public List<Student> getStudents(){	
			return theStudents;
		}
		
		//define endpointfor "/students/{studentId}" - get student at index
		@GetMapping("/students/{studentId}")
		public Student getStudent(@PathVariable int studentId) {
			
			//check student id against list size
			if((studentId >= theStudents.size()) || studentId < 0 )
				throw new StudentNotFoundException("Student id not found -"+studentId);
			return theStudents.get(studentId);
		}
		
}
