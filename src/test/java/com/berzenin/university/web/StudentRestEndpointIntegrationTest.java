package com.berzenin.university.web;

import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = UniversityWebServiceTestApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class StudentRestEndpointIntegrationTest {

	@Autowired
	MockMvc subject;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	StudentRepository studentRepository;

	@Before
	public void setUp() {
		assertThat(subject).isNotNull();
	}

	@Test
	public void testGetAllStudents() throws Exception {
		when(studentRepository.findAll())
				.thenReturn(Arrays.asList(
						new Student(1, "Alex", "Ro"), 
						new Student(2, "Mary", "Bo")));

		subject.perform(get("/students"))
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
	}

	@Test
	public void testAddStudent() throws Exception {
		Student student = new Student("Some", "Name");
		when(studentRepository.save(any())).thenReturn(new Student(1, "Some", "Name"));

		subject.perform(post("/students")
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.content(mapper.writeValueAsBytes(student)))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.name").value("Some"))
			.andExpect(jsonPath("$.surename").value("Name"));

		verify(studentRepository).save(new Student("Some", "Name"));
	}
	
	@Test
	public void testFindStudentById() throws Exception {
		Long id = new Long(1);
		when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(new Student(1, "Fil", "Berzenin")));		
		subject.perform(get("/students/"+id)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print())
			.andExpect(status().isOk());

			verify(studentRepository).getOne(id);
	}

	@Test
	public void testUpdateStudent() throws Exception {
		Long id = new Long(1);
		Student studentForUpdate = new Student(1, "Tima", "Berzenin");
		Student studentWithOldParam = new Student("Tima", "Berzen");
		when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(studentWithOldParam));
		when(studentRepository.save(any())).thenReturn(studentForUpdate);

		subject.perform(put("/students/update/"+id)
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.content(mapper.writeValueAsBytes(studentForUpdate)))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.name").value("Tima"))
			.andExpect(jsonPath("$.surename").value("Berzenin"))
			.andReturn();
	}
	
	@Test
	public void testDeleteStudentsById() throws Exception {
		Long id = new Long(1);
		Student studentForDelete = new Student(1, "Tima", "Berzenin");
		when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(studentForDelete));
		subject.perform(delete("/students/delete/"+id)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(status().isNoContent());
		
		verify(studentRepository).getOne(id);
	}
}