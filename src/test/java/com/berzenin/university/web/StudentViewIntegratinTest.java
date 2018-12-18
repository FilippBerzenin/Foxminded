package com.berzenin.university.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityWebServiceTestApplication.class)
@AutoConfigureMockMvc
public class StudentViewIntegratinTest {

	@Autowired
	MockMvc subject;

	@MockBean
	GroupRepository groupRepository;

	@MockBean
	StudentRepository studentRepository;

	@Test
	public void getStudentsListTest() throws Exception {
		// Given
		Long id = 1L;
		when(studentRepository.findAllStudentsByGroup(id)).thenReturn(Arrays.asList(new Student("first", "first"), new Student("second", "second")));
		// Then
		subject.perform(get("/students/{id}", id))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("students"))
			.andExpect(view().name("students"))
			.andExpect(model().attribute("studentsList", hasSize(2)))
			.andExpect(model().attributeExists("studentsList"));
		// When
		verify(studentRepository).findAllStudentsByGroup(id);
		
		List<Student> students = studentRepository.findAllStudentsByGroup(id);
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
		// Then
		subject.perform(post("/students/create/{id}", id)
			.param("studentsName", newStudentsName)
			.param("studentsSurename", newStudentsSurename))
			.andDo(print())
			.andExpect(forwardedUrl("students"))
			.andExpect(view().name("students"))
			.andExpect(status().isCreated());
		// When
		verify(studentRepository).saveAndFlush(studentForAdd);
	}

	@Test
	public void deleteById() throws Exception {
		// Given
		Long id = 1L;
		Student studentsForDelete = new Student("name", "surename", new Group(1L, "Group"));
		when(studentRepository.findById(id)).thenReturn(Optional.of(studentsForDelete));
		// Then
		subject.perform(get("/students/delete/{id}", id))
			.andExpect(view()
			.name("students"))
			.andDo(print())
			.andExpect(status().isNoContent());
		// When
		verify(studentRepository).findById(id);
	}

	@Test
	public void updateStudentTest() throws Exception {
		// Given
		Long id = 1L;
		String newName = "First";
		String newSurename = "Surename";
		String groupsName = "Second";
		Student studentForUpdate = new Student("First", newSurename);
		Student studentWithOldParam = new Student("Fir", newSurename);
		when(studentRepository.findById(id)).thenReturn(Optional.of(studentWithOldParam));
		when(studentRepository.saveAndFlush(studentForUpdate)).thenReturn(studentForUpdate);
		when(groupRepository.findByName(groupsName)).thenReturn(Optional.ofNullable(new Group("groupsName")));
		// Then
		subject.perform(post("/students/update/{id}", id)
				.param("newStudentName", newName)
				.param("newStudentSurename", newSurename)
				.param("newStudentGroup", groupsName))
		
		.andDo(print())
		.andExpect(forwardedUrl("students"))
		.andExpect(view().name("students"))
		.andExpect(status().isOk());
		// When
		verify(studentRepository).findById(id);
		verify(studentRepository).saveAndFlush(new Student(newName, newSurename, groupRepository.findByName(groupsName).get()));
	}

}
