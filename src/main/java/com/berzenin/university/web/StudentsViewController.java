package com.berzenin.university.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentsViewController {
	
	private final StudentRepository studentRepository;
	
	private final GroupRepository groupRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getAllStudents(@PathVariable("id") Long id, Model model) {
		returnAllStudents(id, model);
		return "students";		
	}
	
	@RequestMapping(value = "/create/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public String createNewStudents(@PathVariable("id") Long id, @RequestParam String studentsName, @RequestParam String studentsSurename, Model model) {
		Group group = groupRepository.getOne(id);
		if (!studentRepository.findByName(studentsName).isPresent() && !studentRepository.findBySurename(studentsSurename).isPresent()) {
			studentRepository.saveAndFlush(new Student(studentsName, studentsSurename, group));
		}
		returnAllStudents(id, model);
		return "students";		
	}
	
	@RequestMapping(value="/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteStudentsById(@PathVariable("id") Long id, Model model) {
		Long group_id = returnStudentIfPresent(id).getGroup().getId();
		studentRepository.deleteById(id);
		returnAllStudents(group_id, model);
		return "students";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String updateGroup(@PathVariable("id") Long id, @RequestParam String newStudentGroup,  @RequestParam String newStudentSurename, @RequestParam String newStudentName, Model model) {
		Student student = returnStudentIfPresent(id);
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
	
	private Student returnStudentIfPresent(long id) {
		return studentRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}
}
