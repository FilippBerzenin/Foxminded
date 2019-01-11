package com.berzenin.university.web.controller;

import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentsViewController {
	
	private final StudentService studentService;
	
	private final GroupService groupService;
	
	private String message = "Something wrong";
	private List<Student> students;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getAllStudents(@PathVariable("id") Long id, Model model) {
		try {
			students = studentService.findAll(id);
			message = "All students from group";
			setModelAttribute(id, model);
			return "students";		
		} catch (Exception e) {
			log.error("Error "+e);
			message = "Error "+e;
			return "error";
		}
	}
	
	@RequestMapping(value = "/create/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public String createNewStudents(@PathVariable("id") Long id, @RequestParam String studentsName, @RequestParam String studentsSurename, Model model) {
		try {
			Student student = new Student(studentsName, studentsSurename, groupService.findById(id));
			if (studentService.searchStudentsByNameAndSurenameForAdd(student)) {
				studentService.save(student);	
				message = "New student created";
				students = studentService.findAll(id);
			} else {
				students = studentService.findAll(id);
				message = "This student is already in the database";
			}
			setModelAttribute(id, model);
			return "students";	
		} catch (Exception e) {
			log.error("Error "+e);
			message = "Error "+e;
			return "error";
		}	
	}
	
	@RequestMapping(value="/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteStudentsById(@PathVariable("id") Long id, Model model) {
		try {
			Long group_id = studentService.getStudentIfPresent(id).getGroup().getId();
			studentService.deleteStudentsById(id);
			message = "Student deleted";
			students = studentService.findAll(group_id);
			setModelAttribute(group_id, model);
			return "students";
		} catch (Exception e) {
			log.error("Error "+e);
			message = "Error "+e;
			return "error";
		}	
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String updateGroup(@PathVariable("id") Long id, @RequestParam String newStudentGroup,  @RequestParam String newStudentSurename, @RequestParam String newStudentName, Model model) {
		Student student = new Student();
		student.setName(newStudentName);
		student.setSurename(newStudentSurename);
		student.setGroup(groupService.searchGroupsByName(newStudentGroup).get(0));
		try {
			if (studentService.searchStudentsByNameAndSurenameForAdd(student)) {
				student = studentService.getStudentIfPresent(id);
				student.setName(newStudentName);
				student.setSurename(newStudentSurename);
				student.setGroup(groupService.searchGroupsByName(newStudentGroup).get(0));
				studentService.save(student);
				message = "Student updated";
			} else {
				message = "This student is already in the database, dosn't updated";
			}
			students = studentService.findAll(groupService.searchGroupsByName(newStudentGroup).get(0).getId());
			setModelAttribute(groupService.searchGroupsByName(newStudentGroup).get(0).getId(), model);
			return "students";
		} catch (Exception e) {
			log.error("Error "+e);
			message = "Error "+e;
			return "error";
		}
	}
	
	private Model setModelAttribute(Long id, Model model) {
		model.addAttribute("message", message);
		model.addAttribute("group_id", id);
		return model.addAttribute("studentsList", students);
	}
}
