package com.berzenin.university.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.berzenin.university.model.university.Excercise;
import com.berzenin.university.service.controller.ExcerciseService;

@Controller
@RequestMapping(value="/excercises")
public class ExcerciseViewController extends GenericViewControllerImpl<Excercise, ExcerciseService> {

	public 	ExcerciseViewController(ExcerciseService service) {
		page = "excercises";
	}
	
	@ModelAttribute("entityFor")
	public Excercise getLoginForm () {
		return new Excercise();
	}
}
