package com.berzenin.university.web;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/students")
public class StudentController {	
	
	private final StudentRepository studentRepository;
	private final GroupRepository groupRepository;
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	List<Student> getAll() {
		return studentRepository.findAll();
	}
	
	@GetMapping(
			value = "/{id}", 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Student getStudentsById(@PathVariable long id) {
		return returnStudentIfPresent(id);
	}

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	Student addStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@PutMapping(
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	Student updateStudent(@RequestBody Student student, @PathVariable("id") long id) {
		Student studentForUpdate = returnStudentIfPresent(id);
		studentForUpdate.setName(student.getName());
		studentForUpdate.setSurename(student.getSurename());
		return studentRepository.save(studentForUpdate);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	Student deleteStudentByEntity (@PathVariable("id") long id) {
		Student student = returnStudentIfPresent(id);
		studentRepository.delete(student);
		return student;
	}

	@PostMapping(value = "/{id}")
	void addStudent(@PathVariable("id") Long id, @RequestParam("groupId") Long groupId) {
		Student student = returnStudentIfPresent(id);
		Group group = groupRepository.findById(id).orElseThrow(NotFoundException::new);
		student.setGroup(group);
		studentRepository.save(student);
	}
	
	private Student returnStudentIfPresent(long id) {
		return studentRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}
}
