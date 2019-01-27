package com.berzenin.university.web.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.model.university.Course;
import com.berzenin.university.model.university.Exercise;
import com.berzenin.university.service.controller.ExcerciseService;

@Controller
@RequestMapping(value="/exercises")
public class ExcerciseViewController extends GenericViewControllerImpl<Exercise, ExcerciseService> {

	public 	ExcerciseViewController(ExcerciseService service) {
		page = "exercises";
	}
	
	@ModelAttribute("entityFor")
	public Exercise getLoginForm () {
		return new Exercise();
	}
	
	@ModelAttribute("course")
	public Course getCourseForm () {
		return new Course();
	}
	
	@Override
	public String add(
			@ModelAttribute("entity") @Valid Exercise entity,
			BindingResult result, 
			Model model) {
		 {
//			 if (result.hasErrors()) {
//				message = "Something wrong with parameters";
//				setModelAttribute(model);
//				return page;
//			}
			try {
				service.save(entity);
				message = "Entity was successful save";
				entites = service.findAll();
				setModelAttribute(model);
				return page;
			} catch (RuntimeException e) {
				this.setModelAttributeWhenthrowException(e, model);
				return page;
			}
		 }
	}
	
	@Override
	public String update(
			@ModelAttribute("entity") @Valid Exercise entity,
			BindingResult result, 
			Model model) {
//		if (result.hasErrors()) {
//			message = "Something wrong with attributes";
//			setModelAttribute(model);
//			return page;
//		}
		try {
			service.update(entity);
			message = "Entity was successful update";
			entites = service.findAll();
			setModelAttribute(model);
			return page;
		} catch (RuntimeException e) {
			this.setModelAttributeWhenthrowException(e, model);
			return page;
		}
	}
	
	@RequestMapping(value = "/addCourse/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String addNewCourseForExercise(
			@PathVariable("id") Long exerciseId,
			@Valid @ModelAttribute("course") Course course, 
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			message = "Error";
			setModelAttribute(model);
			return page;
		}
		try {
			service.addNewCourseForTeacher(exerciseId, course);;
			message = "Course was successful added";
			entites = service.findAll();
			setModelAttribute(model);
			return page;
		} catch (RuntimeException e) {
			this.setModelAttributeWhenthrowException(e, model);
			return page;
		}
	}
	
	@RequestMapping(value = "/removeCourse/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String removeCourseFromExercise(
			@PathVariable("id") Long exerciseId,
			@Valid @ModelAttribute("course") Course course, 
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			message = "Error";
			setModelAttribute(model);
			return page;
		}
		try {
			service.removeCourseFromTeacher(exerciseId, course);;
			message = "Course was successful remove";
			entites = service.findAll();
			setModelAttribute(model);
			return page;
		} catch (RuntimeException e) {
			this.setModelAttributeWhenthrowException(e, model);
			return page;
		}
	}
}
