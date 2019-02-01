package com.berzenin.university.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berzenin.university.dao.ExerciseRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.dao.TeacherRepository;
import com.berzenin.university.model.university.Exercise;
import com.berzenin.university.model.university.TimetableRequest;

@Service
public class TimetableService extends GenericServiceImpl<Exercise, ExerciseRepository> {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;

	public TimetableService(ExerciseRepository repository) {
		super(repository);
	}

	public List<Exercise> findAllExercisesBetweenDatesForStudent(TimetableRequest student) {
		return repository.findByCourses_Groups_Students_IdAndDateBetween(
				studentRepository.findByNameAndSurename(student.getName(), student.getSurename()).get().getId(),
				student.getDateStartSearch(), student.getDateFinishSearch());
	}
	
	public List<Exercise> findAllExercisesBetweenDatesForTeacher(TimetableRequest student) {
		return repository.findByCourses_Teacher_IdAndDateBetween(
				teacherRepository.findByNameAndSurename(student.getName(), student.getSurename()).get().getId(),
				student.getDateStartSearch(), student.getDateFinishSearch());
	}
}