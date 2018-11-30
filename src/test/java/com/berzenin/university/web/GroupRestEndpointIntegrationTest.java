package com.berzenin.university.web;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.berzenin.university.dao.GroupRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Before
	public void setUp() {
		assertThat(subject).isNotNull();
	}
	
	@Test	
	public void testGetAllGroups () throws Exception {
		subject.perform(get("/group/all"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print())
			.andExpect(status().isOk());
	}

}
