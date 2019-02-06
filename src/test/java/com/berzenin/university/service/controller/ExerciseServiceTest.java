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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.berzenin.university.model.university.Course;
import com.berzenin.university.model.university.Exercise;
import com.berzenin.university.web.IntegrationTest;
import com.berzenin.university.web.exception.NotFoundException;

@RunWith(SpringRunner.class)
public class ExerciseServiceTest extends IntegrationTest {
	
	private Exercise first;
	
	@Before
	public void setUp() {
		// Given
		first = new Exercise(0L, "first", LocalDate.of(2019, 7, 15), LocalTime.of(12, 00), LocalTime.of(12, 00), null);
	}

	
	@Test
	public void findAllExercisesTest () {
		//Given
		Exercise second = new Exercise(0L, "second", LocalDate.of(2019, 7, 15), LocalTime.of(12, 00), LocalTime.of(12, 00), null);
		List<Exercise> exercises = Arrays.asList(first,	second);
		when(exerciseService.findAll()).thenReturn(exercises);
		//Then
		List<Exercise> coursesFind = exerciseService.findAll();
		assertThat(exerciseService.findAll(), is(exercises));
		assertThat(exerciseService.findAll(), hasItem(first));
		assertThat(exerciseService.findAll(), hasItems(second, first));
		assertThat(exerciseService.findAll(), not(hasItem(new Exercise())));
		assertThat(coursesFind, hasSize(2));
		assertNotEquals(exerciseService.findAll(), not(hasItem(first)));
		//When
		verify(exerciseService, times(6)).findAll();
	}
	
	@Test
	public void findByIdTest() {
		// Given
		when(exerciseService.findById(first.getId())).thenReturn(first);
		//Then
		assertThat(exerciseService.findById(first.getId()), is(first));
		// When
		verify(exerciseService).findById(first.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void searchExerciseByNameTestNotFoundException() {
		//Given
		when(exerciseService.findById(first.getId())).thenThrow(new NotFoundException());
		//Then
		exerciseService.findById(first.getId());
		// When
		verify(exerciseService).findById(first.getId());
	}
	
	@Test
	public void addNewExerciseTest() {
		// Given
		when(exerciseService.update(first)).thenReturn(first);
		//Then
		assertThat(exerciseService.update(first), is(first));
		// When
		verify(exerciseService).update(first);
	}
	
	@Test
	public void deleteExerciseById () {
		// Given
		//Then
		exerciseService.removeById(first.getId());
		// When	  
	    verify(exerciseService).removeById(first.getId());
	}
	
	@Test
	public void deleteCourseByExercise () {
		// Given
		//Then
		exerciseService.remove(first);
		// When	  
	    verify(exerciseService).remove(first);
	}
	
	public void addNewCourseForExerciseTest() {
		// Given
		Course course = new Course(1L, "Course", null, null, null);
		when(courseService.ifCoursePresentByName("Course")).thenReturn(course);
		when(exerciseRepository.findById(first.getId())).thenReturn(Optional.of(first));
		when(exerciseService.findAll()).thenReturn(Arrays.asList(first));
		//Then
		assertThat(exerciseService.addNewCourseForExercise(0L, course), is(first));
		// When
		verify(exerciseService).addNewCourseForExercise(0L, course);
	}
	
	public void removeNewCourseForExerciseTest() {
		// Given
		Course course = new Course(1L, "Course", null, null, null);
		when(courseService.ifCoursePresentByName("Course")).thenReturn(course);
		when(exerciseRepository.findById(first.getId())).thenReturn(Optional.of(first));
		when(exerciseService.findAll()).thenReturn(Arrays.asList(first));
		//Then
		assertThat(exerciseService.removeCourseFromExercise(0L, course), is(first));
		// When
		verify(exerciseService).removeCourseFromExercise(0L, course);
	}
}
