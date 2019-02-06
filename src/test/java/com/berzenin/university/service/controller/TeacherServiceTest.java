package com.berzenin.university.service.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.berzenin.university.model.persons.Teacher;
import com.berzenin.university.model.university.Course;
import com.berzenin.university.web.IntegrationTest;
import com.berzenin.university.web.exception.NotFoundException;

@RunWith(SpringRunner.class)
public class TeacherServiceTest extends IntegrationTest{
	
	private Teacher first;
	private Teacher second;
	
	@Before
	public void setUp() {
		// Given
		long id = 1L;
		Set<Course> set = new HashSet<>();
		Course course = new Course(id, "course", null, null, null);
		set.add(course);
		first = new Teacher(id, "name", "surename", set);
		second = new Teacher(id, "names", "surenames", set);
	}

	
	@Test
	public void findAllExercisesTest () {
		//Given
		List<Teacher> teachers = Arrays.asList(first,	second);
		when(teacherService.findAll()).thenReturn(teachers);
		//Then
		List<Teacher> coursesFind = teacherService.findAll();
		assertThat(teacherService.findAll(), is(teachers));
		assertThat(teacherService.findAll(), hasItem(first));
		assertThat(teacherService.findAll(), hasItems(second, first));
		assertThat(teacherService.findAll(), not(hasItem(new Teacher())));
		assertThat(coursesFind, hasSize(2));
		assertNotEquals(teacherService.findAll(), not(hasItem(first)));
		//When
		verify(teacherService, times(6)).findAll();
	}
	
	@Test
	public void findByIdTest() {
		// Given
		when(teacherService.findById(first.getId())).thenReturn(first);
		//Then
		assertThat(teacherService.findById(first.getId()), is(first));
		// When
		verify(teacherService).findById(first.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void searchExerciseByNameTestNotFoundException() {
		//Given
		when(teacherService.findById(first.getId())).thenThrow(new NotFoundException());
		//Then
		teacherService.findById(first.getId());
		// When
		verify(teacherService).findById(first.getId());
	}
	
	@Test
	public void addNewExerciseTest() {
		// Given
		when(teacherService.update(first)).thenReturn(first);
		//Then
		assertThat(teacherService.update(first), is(first));
		// When
		verify(teacherService).update(first);
	}
	
	@Test
	public void deleteExerciseById () {
		// Given
		//Then
		teacherService.removeById(first.getId());
		// When	  
	    verify(teacherService).removeById(first.getId());
	}
	
	@Test
	public void deleteExerciseByExercise () {
		// Given
		//Then
		teacherService.remove(first);
		// When	  
	    verify(teacherService).remove(first);
	}
	
	public void addNewCourseForExerciseTest() {
		// Given
		Course course = new Course(1L, "Course", null, null, null);
		when(courseService.ifCoursePresentByName("Course")).thenReturn(course);
		when(teacherRepository.findById(first.getId())).thenReturn(Optional.of(first));
		when(teacherService.findAll()).thenReturn(Arrays.asList(first));
		//Then
		assertThat(teacherService.addNewCourseForTeacher(0L, course), is(first));
		// When
		verify(teacherService).addNewCourseForTeacher(0L, course);
	}
	
	public void removeNewCourseForExerciseTest() {
		// Given
		Course course = new Course(1L, "Course", null, null, null);
		when(courseService.ifCoursePresentByName("Course")).thenReturn(course);
		when(teacherRepository.findById(first.getId())).thenReturn(Optional.of(first));
		when(teacherService.findAll()).thenReturn(Arrays.asList(first));
		//Then
		assertThat(teacherService.removeCourseFromTeacher(0L, course), is(first));
		// When
		verify(teacherService).removeCourseFromTeacher(0L, course);
	}
}
