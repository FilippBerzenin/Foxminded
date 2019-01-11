package com.berzenin.university.web.mvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.IntegrationTest;

@RunWith(SpringRunner.class)
public class StudentViewIntegratinTest extends IntegrationTest  {

	@Test
	public void getStudentsListTest() throws Exception {
		// Given
		Long id = 1L;
		List<Student> allStudents = Arrays.asList(
				new Student("first", "first"),
				new Student("second", "second"));
		when(studentRepository.findByGroupId(id)).thenReturn(allStudents);
		when(studentService.findAll(id)).thenReturn(allStudents);
		// Then
		subject.perform(get("/students/{id}", id))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("students"))
			.andExpect(view().name("students"))
			.andExpect(model().attribute("studentsList", hasSize(2)))
			.andExpect(model().attributeExists("studentsList"));
		// When
		verify(studentService).findAll(id);
		
		List<Student> students = studentRepository.findByGroupId(id);
		assertThat(students.get(0).getName(), is("first"));
		assertThat(students.get(1).getName(), is("second"));
		assertThat(students.get(0).getSurename(), is("first"));
		assertThat(students.get(1).getSurename(), is("second"));
	}

	@Test
	public void addNewStudentTest() throws Exception {
		// Given
		Long id = 1L;
		String newStudentsName = "First";
		String newStudentsSurename = "First";
		Student studentForAdd = new Student(newStudentsName, newStudentsSurename);
		when(studentRepository.saveAndFlush(any())).thenReturn(studentForAdd);
		when(studentService.save(studentForAdd)).thenReturn(studentForAdd);
		// Then
		subject.perform(post("/students/create/{id}", id)
			.param("studentsName", newStudentsName)
			.param("studentsSurename", newStudentsSurename))
			.andDo(print())
			.andExpect(forwardedUrl("students"))
			.andExpect(view().name("students"))
			.andExpect(status().isCreated());
		// When
//		verify(studentService).saveStudent(studentForAdd);
	}

	@Test
	public void deleteByIdTest() throws Exception {
		// Given
		Long id = 1L;
		Group group = new Group(1L, "Group");
		Student studentsForDelete = new Student("name", "surename", group);
		when(studentService.getStudentIfPresent(id)).thenReturn(studentsForDelete);
		// Then
		subject.perform(get("/students/delete/{id}", id))
			.andDo(print())
			.andExpect(status().isOk());
		// When
		verify(studentService).deleteStudentsById(id);
	}

	@Test
	public void updateStudentTest() throws Exception {
		// Given
		Long id = 1L;
		String newName = "First";
		String newSurename = "Surename";
				Group group = new Group (id, "First");
		List<Group> groups = Arrays.asList(group);
		Student studentForUpdate = new Student("First", newSurename, group);
		Student studentWithOldParam = new Student("Fir", newSurename, group);
		when(groupService.searchGroupsByName(group.getName())).thenReturn(groups);
		when(studentService.searchStudentsByNameAndSurenameForAdd(studentForUpdate)).thenReturn(false);
		when(studentService.getStudentIfPresent(1L)).thenReturn(studentWithOldParam);
		when(studentService.save(studentForUpdate)).thenReturn(studentForUpdate);
		// Then
		subject.perform(post("/students/update/{id}", id)
			.param("newStudentName", newName)
			.param("newStudentSurename", newSurename)
			.param("newStudentGroup", group.getName()))		
		.andDo(print())
			.andExpect(forwardedUrl("students"))
			.andExpect(view().name("students"))
			.andExpect(status().isOk());
		// When
		verify(groupService, times(3)).searchGroupsByName(group.getName());
	}

}
