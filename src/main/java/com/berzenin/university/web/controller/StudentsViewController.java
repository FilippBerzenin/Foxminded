package com.berzenin.university.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.service.controller.GroupService;
import com.berzenin.university.service.controller.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentsViewController {
	
	private final StudentService studentService;
	
	private final GroupService groupService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getAllStudents(@PathVariable("id") Long id, Model model) {
		setAllStudents(id, model);
		return "students";		
	}
	
	@RequestMapping(value = "/create/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public String createNewStudents(@PathVariable("id") Long id, @RequestParam String studentsName, @RequestParam String studentsSurename, Model model) {
		Student student = new Student(studentsName, studentsSurename, groupService.findById(id));
		studentService.createNewStudent(student);
		setAllStudents(id, model);
		return "students";		
	}
	
	@RequestMapping(value="/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteStudentsById(@PathVariable("id") Long id, Model model) {
		Long group_id = studentService.getStudentIfPresent(id).getGroup().getId();
		studentService.deleteStudentsById(id);
		getAllStudents(group_id, model);
		return "students";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String updateGroup(@PathVariable("id") Long id, @RequestParam String newStudentGroup,  @RequestParam String newStudentSurename, @RequestParam String newStudentName, Model model) {
		Student student = studentService.getStudentIfPresent(id);
		student.setName(newStudentName);
		student.setSurename(newStudentSurename);
		student.setGroup(groupService.searchGroupByName(newStudentGroup).get(0));
		studentService.saveStudent(student);
		getAllStudents(student.getGroup().getId(), model);
		return "students";		
	}
	
	private Model setAllStudents(Long id, Model model) {
		model.addAttribute("group_id", id);
		return model.addAttribute("studentsList", studentService.getAllStudents(id));
	}
}
