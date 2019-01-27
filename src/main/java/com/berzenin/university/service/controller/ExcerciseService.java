package com.berzenin.university.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berzenin.university.dao.ExcerciseRepository;
import com.berzenin.university.model.university.Course;
import com.berzenin.university.model.university.Exercise;
import com.berzenin.university.web.exception.NotFoundException;

@Service
public class ExcerciseService extends GenericServiceImpl<Exercise, ExcerciseRepository> {
	
	@Autowired
	private CourseService courseService;

	public ExcerciseService(ExcerciseRepository repository) {
		super(repository);
	}
	
	public Exercise update(Exercise entity) {
		Exercise entityForUpdate = repository.findById(entity.getId()).get();
		if (entity.getName()!=null) {
			entityForUpdate.setName(entity.getName());
		}
		if (entity.getDate()!=null) {
			entityForUpdate.setDate(entity.getDate());
		}
		if (entity.getTimeBegin()!=null) {
			entityForUpdate.setTimeBegin(entity.getTimeBegin());
		}
		if (entity.getTimeFinish()!=null) {
			entityForUpdate.setTimeFinish(entity.getTimeFinish());
		}
		return repository.save(entityForUpdate);
	}
	
	public Exercise addNewCourseForTeacher(Long exerciseId, Course course) {
		try {
			Course courseForAdd = courseService.ifCoursePresentByName(course.getSubject());
			Exercise exercise = repository.findById(exerciseId).orElseThrow(NotFoundException::new);
			exercise.getCourses().add(courseForAdd);
			repository.save(exercise);
			return exercise;
		} catch (RuntimeException e) {
			throw new RuntimeException();
		}
	}
	
	public Exercise removeCourseFromTeacher(Long exerciseId, Course course) {
		try {
			Course removeCourse = courseService.ifCoursePresentByName(course.getSubject());
			Exercise exercise = repository.findById(exerciseId).orElseThrow(NotFoundException::new);
			exercise.getCourses().remove(removeCourse);
			repository.save(exercise);
			return exercise;
		} catch (RuntimeException e) {
			throw new RuntimeException();
		}
	}

}
