package com.berzenin.university.web;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.dao.StudentRepository;
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

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UniversityWebServiceTestApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class GroupRestEndpointIntegrationTest {
	
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
		when(repository.findAll()).thenReturn(Collections.emptyList());
		subject.perform(get("/group/all"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
