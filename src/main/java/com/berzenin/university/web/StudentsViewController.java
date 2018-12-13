package com.berzenin.university.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentsViewController {
	
	private final StudentRepository studentRepository;
	
	private final GroupRepository groupRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getAllStudents(@PathVariable("id") Long id, Model model) {
		returnAllStudents(id, model);
		return "students";		
	}
	
	@RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
	public String createNewStudents(@PathVariable("id") Long id, @RequestParam String studentsName, @RequestParam String studentsSurename, Model model) {
		Student student = new Student (studentsName, studentsSurename, groupRepository.getOne(id));
		studentRepository.saveAndFlush(student);
		returnAllStudents(id, model);
		return "students";		
	}
	
	private Model returnAllStudents(Long id, Model model) {
		model.addAttribute("group.id", id);
		return model.addAttribute("studentsList", groupRepository
				.findById(id)
				.get()
				.getStudents());
	}
}
