package com.berzenin.university.service.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.IntegrationTest;
import com.berzenin.university.web.exception.NotFoundException;

@RunWith(SpringRunner.class)
public class StudentServiceTest extends IntegrationTest{
	
	@SuppressWarnings("unchecked")
	@Test
	public void findAllGroupTest() {
		//Given
		Student first = new Student(1L, "First", "First");
		Student second = new Student(2L, "Second", "Second");
		List<Student> students = Arrays.asList(first, second);
		when(studentService.findAll(1L)).thenReturn(students);
		//Then
		assertThat(studentService.findAll(1L), is(students));
		assertThat(studentService.findAll(1L), hasItem(first));
		assertThat(studentService.findAll(1L), hasItems(second, first));
		assertThat(studentService.findAll(1L), not(hasItem(new Student())));
		assertNotEquals(studentService.findAll(1L), not(hasItem(first)));
		assertThat(studentService.findAll(1L), hasSize(2));
		assertThat(studentService.findAll(1L), contains(
				hasProperty("name", is("First")),
				hasProperty("name", is("Second"))
				));
	}
	
	@Test
	public void addNewStudentTest() {
		// Given		
		Group group = new Group(1L, "Group");
		Student first = new Student(1L, "First", "First", group);
		when(studentService.searchStudentsByNameAndSurenameForAdd(first)).thenReturn(true);
		when(studentService.save(first)).thenReturn(first);		
		when(studentRepository.saveAndFlush(first)).thenReturn(first);
//		when(studentService.createNewStudent(first)).thenReturn(first);
		//Then
//		assertThat(studentService.createNewStudent(first)
//				.getName().equals("First"));
		assertThat(studentService.save(first), is(first));
		// When
		verify(studentService).save(first);
	}
	
	@Test
	public void searchGroupsByNameTest() {
		//Given
		Group group = new Group(1L, "Group");
		Student first = new Student(1L, "First", "First", group);
		List<Student> students = Arrays.asList(first);
		when(studentService.searchStudentsByNameAndSurename(first.getName(), first.getSurename(), first.getGroup().getId())).thenReturn(students);
		//Then
		assertThat(studentService.searchStudentsByNameAndSurename(first.getName(), first.getSurename(), first.getGroup().getId()), is(students));
		//When
		verify(studentService).searchStudentsByNameAndSurename(first.getName(), first.getSurename(), first.getGroup().getId());
	}
	
	@Test(expected = NotFoundException.class)
	public void searchGroupsByNameTestNotFoundException() {
		//Given
		Group group = new Group(1L, "Group");
		Student first = new Student(1L, "First", "First", group);
		when(studentService.searchStudentsByNameAndSurename(first.getName(), first.getSurename(), first.getGroup().getId())).thenThrow(new NotFoundException());
		//Then
		studentService.searchStudentsByNameAndSurename(first.getName(), first.getSurename(), first.getGroup().getId());
		// When
		verify(studentService).searchStudentsByNameAndSurename(first.getName(), first.getSurename(), first.getGroup().getId());
	}
	
	@Test
	public void findByIdTest() {
		// Given
		Group group = new Group(1L, "Group");
		Student first = new Student(1L, "First", "First", group);
		when(studentService.findById(first.getId())).thenReturn(first);
		//Then
		assertThat(studentService.findById(first.getId()), is(first));
		// When
		verify(studentService).findById(first.getId());
	}
	
	@Test (expected = NotFoundException.class)
	public void notFindByIdTest() {
		// Given
		when(studentService.findById(2L)).thenThrow(new NotFoundException());
		//Then
		studentService.findById(2L);
		// When
		verify(studentService).findById(2L);
	}
	
	@Test
	public void deleteStudentById () {
		// Given
		long id = 1;
		//Then
		studentService.deleteStudentsById(id);
		// When
		verify(studentService).deleteStudentsById(1L);
	}
}
