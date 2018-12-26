package com.berzenin.university.service.controller;

import java.util.List;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	private final StudentRepository studentRepository;

	public List<Student> getAllStudents(long id) {
		return studentRepository.findByGroupId(id);
	}
	
	public Student findById (long id) {
		return studentRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}
	
	public Student createNewStudent (Student student) {
		if (!studentRepository.findByName(student.getName()).isPresent() && !studentRepository.findBySurename(student.getSurename()).isPresent()) {
			saveStudent(student);
		}
		return student;		
	}
	
	public void saveStudent (Student student) {
		studentRepository.saveAndFlush(student);
	}
	
	public Student deleteStudentsById(long id) {
		studentRepository.deleteById(id);
		return null;
	}
	
	public Student getStudentIfPresent(long id) {
		return studentRepository.findById(id).get();
	}
}
