package com.berzenin.university.service.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.berzenin.university.dao.TeacherRepository;
import com.berzenin.university.model.persons.Teacher;

@Service
public class TeacherService extends GenericServiceImpl<Teacher, CrudRepository<Teacher,Long>> {

	public TeacherService(TeacherRepository repository) {
		super(repository);
	}

}
