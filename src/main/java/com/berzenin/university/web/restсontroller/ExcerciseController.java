package com.berzenin.university.web.restсontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berzenin.university.model.university.Excercise;
import com.berzenin.university.service.controller.ExcerciseService;

@RestController
@RequestMapping(value="/api/excercise")
public class ExcerciseController extends GenericControllerImpl<Excercise, ExcerciseService> {

	public ExcerciseController(ExcerciseService service) {
		super(service);
	}

}
