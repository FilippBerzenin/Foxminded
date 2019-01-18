package com.berzenin.university.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.web.exception.NotFoundException;

@Service
public class StudentService extends GenericServiceImpl<Student, StudentRepository> {
	
	@Autowired
	public StudentService(StudentRepository repository) {
		super(repository);
	}

	@Autowired
	private GroupRepository groupRepository;
	
	

	public List<Student> findAll(long id) {
		return repository.findByGroupId(id);
	}
	
	public Student addStudent (Student student, long group_id) {
		student.setGroup(groupRepository.findById(group_id).get());
		if (!this.ifStudentPresent(student)) {
			return repository.saveAndFlush(student);
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
			return repository.saveAndFlush(updateStudent);
		} else {
			throw new NotFoundException();
		}
	}
	
	public boolean ifStudentPresent (Student student) {
			 if (repository.findByNameAndSurenameAndGroupName(
					 student.getName(), 
					 student.getSurename(), 
					 student.getGroup().getName())
					 .isPresent())
			 {
				 return true;
			 }
			 return false;
	}
}
