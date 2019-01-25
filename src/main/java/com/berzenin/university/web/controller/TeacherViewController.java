package com.berzenin.university.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.berzenin.university.model.persons.Teacher;
import com.berzenin.university.service.controller.TeacherService;

@Controller
@RequestMapping(value="/teachers")
public class TeacherViewController extends GenericViewControllerImpl<Teacher, TeacherService> {
	
	public 	TeacherViewController(TeacherService service) {
		page = "teachers";
	}
	
	@ModelAttribute("entityFor")
	public Teacher getLoginForm () {
		return new Teacher();
	}
}
