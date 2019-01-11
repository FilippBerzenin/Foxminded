package com.berzenin.university.service.controller;

import java.util.Arrays;
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

	public List<Student> findAll(long id) {
		return studentRepository.findByGroupId(id);
	}
	
	public Student findById (long id) {
		return studentRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}
	
	public boolean searchStudentsByNameAndSurenameForAdd (Student student) {
			 if (studentRepository.findByNameAndSurenameAndGroupId(student.getName(), student.getSurename(), student.getGroup().getId()).isPresent()) {
				 return false;
			 }
			 return true;
	}
	
	public List<Student> searchStudentsByNameAndSurename (String nameFoSearch, String surenameFoSearch, Long id) {
 		if (nameFoSearch != null && !nameFoSearch.isEmpty()) {
			 return Arrays.asList(studentRepository.findByNameAndSurenameAndGroupId(nameFoSearch, surenameFoSearch, id)
					.orElseThrow(NotFoundException::new));
		} else {
				return Arrays.asList();
		}
	}
	
	public Student save(Student student) {
		return studentRepository.saveAndFlush(student);
	}
	
	public void deleteStudentsById(long id) {
			studentRepository.delete(getStudentIfPresent(id));
	}
	
	public Student getStudentIfPresent(long id) {
		return studentRepository.findById(id).get();
	}
}
