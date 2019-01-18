package com.berzenin.university.service.controller;

import org.springframework.data.repository.CrudRepository;

import com.berzenin.university.dao.TeacherRepository;
import com.berzenin.university.model.persons.Teacher;

public class TeachersService extends GenericServiceImpl<Teacher, CrudRepository<Teacher,Long>> {

	public TeachersService(TeacherRepository repository) {
		super(repository);
	}

}
