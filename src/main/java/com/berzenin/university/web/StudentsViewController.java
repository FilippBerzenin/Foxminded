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
import com.berzenin.university.model.university.Group;

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
	
	@RequestMapping(value = "/create/{id}")
	public String createNewStudents(@PathVariable("id") Long id, @RequestParam String studentsName, @RequestParam String studentsSurename, Model model) {
		Group group = groupRepository.getOne(id);
		Student student = new Student (studentsName, studentsSurename, group);
		studentRepository.saveAndFlush(student);
		returnAllStudents(id, model);
		return "students";		
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteStudentsById(@PathVariable("id") Long id, Model model) {
		Long group_id = studentRepository.findById(id).get().getGroup().getId();
		studentRepository.deleteById(id);
		returnAllStudents(group_id, model);
		return "students";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateGroup(@PathVariable("id") Long id, @RequestParam String newStudentGroup,  @RequestParam String newStudentSurename, @RequestParam String newStudentName, Model model) {
		Student student = studentRepository.findById(id).get();
		student.setName(newStudentName);
		student.setSurename(newStudentSurename);
		student.setGroup(groupRepository.findByName(newStudentGroup).get());
		studentRepository.saveAndFlush(student);
		returnAllStudents(student.getGroup().getId(), model);
		return "students";		
	}
	
	private Model returnAllStudents(Long id, Model model) {
		model.addAttribute("group_id", id);
		return model.addAttribute("studentsList", studentRepository.findAllStudentsByGroup(id));
	}
}
