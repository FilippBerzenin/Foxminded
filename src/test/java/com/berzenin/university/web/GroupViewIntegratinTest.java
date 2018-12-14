package com.berzenin.university.web;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = UniversityWebServiceTestApplication.class)
public class GroupViewIntegratinTest {

	
	@Autowired
	MockMvc subject;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	GroupRepository repository;

	@MockBean
	StudentRepository studentRepository;

	@Before
	public void setUp() {
		assertThat(subject).isNotNull();
	}
	
	@Test	
	public void testGetAllGroups () throws Exception {
		when(repository.findAll()).thenReturn(Arrays.asList(
				new Group(1, "test", null),
				new Group(2, "second", null)));
		subject.perform(get("/groups"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].name").value("test"))
		.andExpect(jsonPath("$[1].id").value(2))
		.andExpect(jsonPath("$[1].name").value("second"));
	}
	
	@Test
	public void testAddNewGroup() throws Exception {
		Group group = new Group("first"); 
		when(repository.saveAndFlush(any())).thenReturn(new Group(2, "first", null));

		subject.perform(post("/groups")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapper.writeValueAsBytes(group))
				)
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(jsonPath("$.id").value(2))
				.andExpect(jsonPath("$.name").value("first"));
	}
	
	@Test
	public void testFindGroupById () throws Exception {
		when(repository.findById(1L)).thenReturn(Optional.of(new Group("first")));
		
		subject.perform(get("/groups/"+1L)
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(0))
		.andExpect(jsonPath("$.name").value("first"));
		
		verify(repository).findById(1L);
	}
	
	@Test
	public void notFindById() throws Exception {
		Long id = 2L;
		when(repository.findById(id)).thenThrow(new NotFoundException());

		subject.perform(get("/groups/" + id)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(status().reason(containsString("Student Not Found")));

		verify(repository).findById(id);
	}
	
	@Test
	public void testUpdateGroup() throws Exception {
		Long id = 1L;
		Group groupForUpdate = new Group(id, "First", null);
		Group groupWithOldParam = new Group(id, "Fir", null);
		when(repository.findById(id)).thenReturn(Optional.of(groupWithOldParam));
		when(repository.save(any())).thenReturn(groupForUpdate);

		subject.perform(put("/groups/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapper.writeValueAsBytes(groupForUpdate)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value("First"))
				.andReturn();

		verify(repository).findById(id);
		verify(repository).save(new Group(id, "First", null));
	}
	
	@Test
	public void testGetAllStudentsFromGroup () throws Exception {
		when(repository.findById(1L)).thenReturn(Optional.of(new Group(1L, "test", 
				Arrays.asList(new Student(1, "Alex", "Ro", new Group(1L, "test", null)), 
							  new Student(2, "Mary", "Bo", new Group(1L, "test", null)))
				)));

		subject.perform(get("/groups/"+1L+"/students"))
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
		
		verify(repository).findById(1L);
	}
	
	@Test
	public void testDeleteGroupsById() throws Exception {
		Long id = 1L;
		Group groupsForDelete = new Group(id, "test", null);
		when(repository.findById(id)).thenReturn(Optional.of(groupsForDelete));
		subject.perform(delete("/groups/" + id)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print())
				.andExpect(status().isNoContent());

		verify(repository).findById(id);
	}
}
