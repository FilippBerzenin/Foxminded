package com.berzenin.university.service.controller;

import java.util.List;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	private final StudentRepository studentRepository;
	private final GroupRepository groupRepository;

	public List<Student> findAll(long id) {
		return studentRepository.findByGroupId(id);
	}
	
	public Student addStudent (Student student, long group_id) {
		student.setGroup(groupRepository.findById(group_id).get());
		if (!this.ifStudentPresent(student)) {
			return studentRepository.saveAndFlush(student);
		} else {
			throw new NotFoundException();
		}
	}
	
	public Student updateStudent (Student student) {
		if (!this.ifStudentPresent(student)) {
			Student updateStudent = new Student();
			updateStudent = this.findById(student.getId());
			updateStudent.setName(student.getName());
			updateStudent.setSurename(student.getName());
			updateStudent.setGroup(groupRepository.findByName(student.getGroup().getName()).get());
			return studentRepository.saveAndFlush(updateStudent);
		} else {
			throw new NotFoundException();
		}
	}
	
	public Student findById (long id) {
		return studentRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}
	
	public boolean ifStudentPresent (Student student) {
			 if (studentRepository.findByNameAndSurenameAndGroupName(
					 student.getName(), 
					 student.getSurename(), 
					 student.getGroup().getName())
					 .isPresent())
			 {
				 return true;
			 }
			 return false;
	}
	
	public void deleteStudentsById(long id) {
			studentRepository.delete(findById(id));
	}
}
