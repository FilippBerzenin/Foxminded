package com.berzenin.university.service.controller;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.ExcerciseRepository;
import com.berzenin.university.model.university.Excercise;

@Service
public class ExcerciseService extends GenericServiceImpl<Excercise, ExcerciseRepository> {

	public ExcerciseService(ExcerciseRepository repository) {
		super(repository);
	}

}
