package com.berzenin.university.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.berzenin.university.dao.TeacherRepository;
import com.berzenin.university.model.persons.Teacher;
import com.berzenin.university.model.university.Course;
import com.berzenin.university.web.exception.NotFoundException;

@Service
public class TeacherService extends GenericServiceImpl<Teacher, CrudRepository<Teacher,Long>> {
	
	@Autowired
	private CourseService courseService;

	public TeacherService(TeacherRepository repository) {
		super(repository);
	}
	
	public Teacher addNewCourseForTeacher(Long teacherId, Course course) {
		try {
			Course courseForAdd = courseService.ifCoursePresentByName(course.getSubject());
			Teacher teacher = repository.findById(teacherId).orElseThrow(NotFoundException::new);
			teacher.getCourses().add(courseForAdd);
			repository.save(teacher);
			return teacher;
		} catch (RuntimeException e) {
			throw new RuntimeException();
		}
	}

}
