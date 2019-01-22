package com.berzenin.university.web.restсontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.service.controller.StudentService;

@RestController
@RequestMapping(value="/api/students")
public class StudentController extends GenericControllerImpl<Student, StudentService> {	
	
	public StudentController(StudentService service) {
		super(service);
	}
}
