package com.berzenin.university.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {

	
	@Autowired
	private final StudentRepository studentRepository;

	@GetMapping(
			value = "/students", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	List<Student> getAll() {
		return studentRepository.findAll();
	}
	
	@GetMapping(
			value = "/students/{id}", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Student getStudentsById(@PathVariable("id") long id) {
		return studentRepository.getOne(id);
	}

	@PostMapping(
			value = "/students", 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	Student addStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@PutMapping(
			value = "/students/update/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	Student updateStudent(@RequestBody Student student, @PathVariable("id") long id) {
		Student studentForUpdate = studentRepository.findById(id).get();
		studentForUpdate.setName(student.getName());
		studentForUpdate.setSurename(student.getSurename());
		return studentRepository.save(studentForUpdate);
	}
	
	@DeleteMapping(value = "/students/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	Student deleteStudentByEntity (@PathVariable("id") long id) {
		Student student = studentRepository.getOne(id);
		studentRepository.delete(student);
		return student;
	}
}
