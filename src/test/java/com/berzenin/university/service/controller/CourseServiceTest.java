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
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.berzenin.university.model.university.Course;
import com.berzenin.university.web.IntegrationTest;
import com.berzenin.university.web.exception.NotFoundException;

@RunWith(SpringRunner.class)
public class CourseServiceTest extends IntegrationTest {
	
	@Test
	public void findAllCoursesTest () {
		//Given
		Course first = new Course(0L, "first", null, null, null);
		Course second = new Course(1L, "second", null, null, null);
		List<Course> courses = Arrays.asList(first,	second);
		when(courseService.findAll()).thenReturn(courses);
		//Then
		List<Course> coursesFind = (List<Course>) courseService.findAll();
		assertThat(courseService.findAll(), is(courses));
		assertThat(courseService.findAll(), hasItem(first));
		assertThat(courseService.findAll(), hasItems(second, first));
		assertThat(courseService.findAll(), not(hasItem(new Course())));
		assertThat(coursesFind, hasSize(2));
		assertNotEquals(courseService.findAll(), not(hasItem(first)));
		//When
		verify(courseService, times(6)).findAll();
	}
	
	@Test
	public void findByIdTest() {
		// Given
		Course first = new Course(0L, "first", null, null, null);
		when(courseService.findById(first.getId())).thenReturn(first);
		//Then
		assertThat(courseService.findById(first.getId()), is(first));
		// When
		verify(courseService).findById(first.getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void searchCourseByNameTestNotFoundException() {
		//Given
		Course first = new Course(0L, "first", null, null, null);
		when(courseService.findById(first.getId())).thenThrow(new NotFoundException());
		//Then
		courseService.findById(first.getId());
		// When
		verify(courseService).findById(first.getId());
	}
	
	@Test
	public void addNewCourseTest() {
		// Given
		Course first = new Course(0L, "first", null, null, null);
		when(courseService.update(first)).thenReturn(first);
		//Then
		assertThat(courseService.update(first), is(first));
		// When
		verify(courseService).update(first);
	}
	
	@Test
	public void deleteCourseById () {
		// Given
		Course first = new Course(0L, "first", null, null, null);
		//Then
		courseService.removeById(first.getId());
		// When	  
	    verify(courseService).removeById(first.getId());
	}
	
	@Test
	public void deleteCourseByCourse () {
		// Given
		Course first = new Course(0L, "first", null, null, null);
		//Then
		courseService.remove(first);
		// When	  
	    verify(courseService).remove(first);
	}
	
	public void ifCoursePresentByNameTest () {
		// Given
		String courseName = "first";
		Course first = new Course(0L, "first", null, null, null);
		when(courseService.ifCoursePresentByName(courseName)).thenReturn(first);
		//Then
		assertThat(courseService.ifCoursePresentByName(courseName), is(first));
		// When	  
	    verify(courseService).remove(first);
	}
}
