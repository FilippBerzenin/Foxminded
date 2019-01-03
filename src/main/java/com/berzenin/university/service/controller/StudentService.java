package com.berzenin.university.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	public Student createNewStudent (Student student) {
		Student empty = new Student();
		if (searchStudentsByNameAndSurenameForAdd(student)) {
			save(student);
			return student;
		}
		return empty;	
	}
	
	public boolean searchStudentsByNameAndSurenameForAdd (Student student) {
			 if (studentRepository.findByNameAndSurename(student.getName(), student.getSurename()).isPresent()) {
				 return false;
			 }
			 return true;
	}
	
	public List<Student> searchStudentsByNameAndSurename (long id, String nameFoSearch, String surenameFoSearch) {
 		if (nameFoSearch != null && !nameFoSearch.isEmpty()) {
			 return Arrays.asList(studentRepository.findByNameAndSurename(nameFoSearch, surenameFoSearch)
					.orElseThrow(NotFoundException::new));
		} else {
				return findAll(id);
		}
	}
	
	public Student save (Student student) {
		return studentRepository.saveAndFlush(student);
	}
	
	public String deleteStudentsById(long id) {
		try {
			studentRepository.delete(getStudentIfPresent(id));
			return id+ " Successfully deleted.";
		}catch (RuntimeException e) {
			log.info("Error with delete" + e);
			return id+ " Dosn't deleted.";
		}
	}
	
	public Student getStudentIfPresent(long id) {
		return studentRepository.findById(id).get();
	}
}
