package com.berzenin.university.service.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.persons.Teacher;
import com.berzenin.university.model.university.Exercise;
import com.berzenin.university.web.IntegrationTest;
import com.berzenin.university.web.dto.TimetableRequest;

@RunWith(SpringRunner.class)
public class TimetableServiceTest extends IntegrationTest {
	
	private Exercise first;
	private Exercise second;
	
	@Before
	public void setUp() {
		// Given
		first = new Exercise(0L, "first", LocalDate.of(2019, 7, 15), LocalTime.of(12, 00), LocalTime.of(12, 00), null);
		second = new Exercise(0L, "second", LocalDate.of(2019, 7, 15), LocalTime.of(12, 00), LocalTime.of(12, 00), null);
	}
	
	@Test
	public void findAllExercisesBetweenDatesForStudentTest() {
		//Given
		TimetableRequest timetableRequest = new TimetableRequest(0L, "Student", "Student", LocalDate.of(2019, 02, 20), LocalDate.of(2019, 02, 20));
		Student student = new Student(0L,"Student", "Student");
		List<Exercise> exercises = Arrays.asList(first,	second);
		when(studentRepository.findByNameAndSurename(student.getName(), student.getSurename())).thenReturn(Optional.of(student));
		when(exerciseRepository.findByCourses_Groups_Students_IdAndDateBetween(
				student.getId(), 
				timetableRequest.getDateStartSearch(), 
				timetableRequest.getDateFinishSearch()))
		.thenReturn(exercises);
		when(timetableService.findAllExercisesBetweenDatesForStudent(timetableRequest)).thenReturn(exercises);
		//Then
		assertThat(timetableService.findAllExercisesBetweenDatesForStudent(timetableRequest), is(exercises));
		//When
		verify(timetableService).findAllExercisesBetweenDatesForStudent(timetableRequest);
	}
	
	@Test
	public void findAllExercisesBetweenDatesForTeacherTest() {
		//Given
		TimetableRequest timetableRequest = new TimetableRequest(0L, "Student", "Student", LocalDate.of(2019, 02, 20), LocalDate.of(2019, 02, 20));
		Teacher teacher = new Teacher(0L,"Student", "Student", null);
		List<Exercise> exercises = Arrays.asList(first,	second);
		when(teacherRepository.findByNameAndSurename(teacher.getName(), teacher.getSurename())).thenReturn(Optional.of(teacher));
		when(exerciseRepository.findByCourses_Teacher_IdAndDateBetween(
				teacher.getId(), 
				timetableRequest.getDateStartSearch(), 
				timetableRequest.getDateFinishSearch()))
		.thenReturn(exercises);
		when(timetableService.findAllExercisesBetweenDatesForTeacher(timetableRequest)).thenReturn(exercises);
		//Then
		assertThat(timetableService.findAllExercisesBetweenDatesForTeacher(timetableRequest), is(exercises));
		//When
		verify(timetableService).findAllExercisesBetweenDatesForTeacher(timetableRequest);
	}

}
