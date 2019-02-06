package com.berzenin.university.service.controller;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.ExerciseRepository;
import com.berzenin.university.model.university.Course;
import com.berzenin.university.model.university.Exercise;
import com.berzenin.university.web.exception.NotFoundException;

@Service
public class ExerciseService extends GenericServiceImpl<Exercise, ExerciseRepository> {

	private final CourseService courseService;

	public ExerciseService(ExerciseRepository repository, CourseService courseService) {
		super(repository);
		this.courseService=courseService;
	}
	
	//TODO
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
	
	public Exercise addNewCourseForExercise(Long exerciseId, Course course) {
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
	
	public Exercise removeCourseFromExercise(Long exerciseId, Course course) {
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
