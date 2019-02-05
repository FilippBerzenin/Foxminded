package com.berzenin.university.service.controller;

import java.util.List;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.ExerciseRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.dao.TeacherRepository;
import com.berzenin.university.model.university.Exercise;
import com.berzenin.university.web.dto.TimetableRequest;

@Service
public class TimetableService extends GenericServiceImpl<Exercise, ExerciseRepository> {

	private final StudentRepository studentRepository;
	
	private final TeacherRepository teacherRepository;

	public TimetableService(ExerciseRepository repository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
		super(repository);
		this.studentRepository=studentRepository;
		this.teacherRepository=teacherRepository;
	}

	public List<Exercise> findAllExercisesBetweenDatesForStudent(TimetableRequest student) {
		return repository.findByCourses_Groups_Students_IdAndDateBetween(
				studentRepository.findByNameAndSurename(student.getName(), student.getSurename()).get().getId(),
				student.getDateStartSearch(), student.getDateFinishSearch());
	}
	
	public List<Exercise> findAllExercisesBetweenDatesForTeacher(TimetableRequest teacher) {
		return repository.findByCourses_Teacher_IdAndDateBetween(
				teacherRepository.findByNameAndSurename(teacher.getName(), teacher.getSurename()).get().getId(),
				teacher.getDateStartSearch(), teacher.getDateFinishSearch());
	}
}