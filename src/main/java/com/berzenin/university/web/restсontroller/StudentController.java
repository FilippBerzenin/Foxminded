package com.berzenin.university.web.restсontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.service.controller.GroupService;
import com.berzenin.university.service.controller.StudentService;

@RestController
//@RequiredArgsConstructor
@RequestMapping(value="/api/students")
public class StudentController extends GenericControllerImpl<Student, StudentService> {	
	
	public StudentController(StudentService service) {
		super(service);
	}
	
	@Autowired
	private GroupService groupRepository;
	
	@Override
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public
	List<Student> getAll() {
		return service.findAll();
	}
	
//	@GetMapping(
//			value = "/{id}", 
//			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
//			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	Student getStudentsById(@PathVariable long id) {
//		return service.findById(id);
//	}
//
//	@PostMapping(
//			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
//			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseStatus(HttpStatus.CREATED)
//	Student addStudent(@RequestBody Student student) {
//		return service.saveOrUpdate(student);
//	}
//	
//	@PutMapping(
//			value = "/{id}",
//			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
//			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	Student updateStudent(@RequestBody Student student, @PathVariable("id") long id) {
//		Student studentForUpdate = service.findById(id);
//		studentForUpdate.setName(student.getName());
//		studentForUpdate.setSurename(student.getSurename());
//		return service.saveOrUpdate(studentForUpdate);
//	}
//	
//	@DeleteMapping(value = "/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	Student deleteStudentByEntity (@PathVariable("id") long id) {
//		Student student = service.findById(id);
//		service.remove(student);
//		return student;
//	}
//
//	@PostMapping(value = "/{id}")
//	void addStudent(@PathVariable("id") Long id, @RequestParam("groupId") Long groupId) {
//		Student student = service.findById(id);
//		Group group = groupRepository.findById(id);
//		student.setGroup(group);
//		service.saveOrUpdate(student);
//	}
	
//	private Student returnStudentIfPresent(long id) {
//		return studentRepository.findById(id)
//				.orElseThrow(NotFoundException::new);
//	}
}
