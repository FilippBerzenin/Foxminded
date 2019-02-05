package com.berzenin.university.service.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.berzenin.university.model.university.Course;
import com.berzenin.university.web.IntegrationTest;

@RunWith(SpringRunner.class)
public class CourseServiceTest extends IntegrationTest {
	
	@Test
	public void findAllCoursesTest () {
		//Given
		Course first = new Course(0L, "first", null, null, null);
		Course second = new Course(1L, "second", null, null, null);
		List<Course> courses = Arrays.asList(first,	second);
		when(courseRepository.findAll()).thenReturn(courses);
		//Then
		//When
		assertThat(courseRepository.findAll(), is(courses));
		assertThat(courseRepository.findAll(), hasItem(first));
		assertThat(courseRepository.findAll(), hasItems(second, first));
		assertThat(courseRepository.findAll(), not(hasItem(new Course())));
		assertNotEquals(courseRepository.findAll(), not(hasItem(first)));
	}

}
