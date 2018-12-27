package com.berzenin.university.web.rest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.UniversityWebServiceTestApplication;
import com.berzenin.university.web.exception.NotFoundException;

@SpringBootTest(classes = UniversityWebServiceTestApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class StudentRestEndpointIntegrationTest extends RestIntegrationTest {

	@Before
	public void setUp() {
		assertThat(subject).isNotNull();
	}

	@Test
	public void testGetAllStudents() throws Exception {
		// Given
		when(studentRepository.findAll())
				.thenReturn(Arrays.asList(
						new Student(1, "Alex", "Ro"), 
						new Student(2, "Mary", "Bo")));
		// Then
		subject.perform(get("/api/students"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Alex"))
				.andExpect(jsonPath("$[0].surename").value("Ro"))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].name").value("Mary"))
				.andExpect(jsonPath("$[1].surename").value("Bo"));
		// When
		verify(studentRepository).findAll();
	}

	@Test
	public void testAddStudent() throws Exception {
		// Given
		Student student = new Student("Some", "Name");
		when(studentRepository.save(any())).thenReturn(new Student(1, "Some", "Name"));
		// Then
		subject.perform(post("/api/students")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapper.writeValueAsBytes(student)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Some"))
				.andExpect(jsonPath("$.surename").value("Name"));
		// When
		verify(studentRepository).save(new Student("Some", "Name"));
	}

	@Test
	public void testFindStudentById() throws Exception {
		// Given
		Long id = 1L;
		when(studentRepository.findById(any())).thenReturn(Optional.of(
				new Student(id, "Fil", "Berzenin", null)));
		// Then
		subject.perform(get("/api/students/" + id)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value("Fil"))
				.andExpect(jsonPath("$.surename").value("Berzenin"));
		// When
		verify(studentRepository).findById(id);
	}

	@Test()
	public void notFindById() throws Exception {
		// Given
		Long id = 2L;
		when(studentRepository.findById(id)).thenThrow(new NotFoundException());
		// Then
		subject.perform(get("/api/students/" + id)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(status().reason(containsString("Items Not Found")));
		// When
		verify(studentRepository).findById(id);
	}

	@Test
	public void testUpdateStudent() throws Exception {
		// Given
		Long id = 1L;
		Student studentForUpdate = new Student(1, "Tima", "Berzenin");
		Student studentWithOldParam = new Student("Tima", "Berzen");
		when(studentRepository.findById(id)).thenReturn(Optional.of(studentWithOldParam));
		when(studentRepository.save(any())).thenReturn(studentForUpdate);
		// Then
		subject.perform(put("/api/students/" + id)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapper.writeValueAsBytes(studentForUpdate)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value("Tima"))
				.andExpect(jsonPath("$.surename").value("Berzenin")).andReturn();
		// When
		verify(studentRepository).findById(id);
		verify(studentRepository).save(new Student(0, "Tima", "Berzenin"));
	}

	@Test
	public void testDeleteStudentsById() throws Exception {
		// Given
		Long id = 1L;
		Student studentForDelete = new Student(1, "Tima", "Berzenin");
		when(studentRepository.findById(id)).thenReturn(Optional.of(studentForDelete));
		// Then
		subject.perform(delete("/api/students/" + id)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(status().isNoContent());
		// When
		verify(studentRepository).findById(id);
	}

	@Test
	public void testAddStudentToGroup() throws Exception {
		// Given
		when(groupRepository.findById(1L)).thenReturn(Optional.of(new Group(1L, "Some Group", null)));
		when(studentRepository.findById(1L)).thenReturn(Optional.of(new Student(1L, "Some", "Name")));
		// Then
		subject.perform(post("/api/students/1?groupId=1"))
		.andExpect(status().isOk());
		// When
		verify(studentRepository).save(new Student(1L, "Some", "Name", new Group(1L, "Some Group", null)));
	}
}