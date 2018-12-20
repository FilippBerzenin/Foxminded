package com.berzenin.university.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityWebServiceTestApplication.class)
@AutoConfigureMockMvc
public class IndexIntegrationTest {

	@Autowired
	MockMvc object;
	
	@MockBean
	GroupRepository groupRepository;

	@MockBean
	StudentRepository studentRepository;
	
	@Test
	public void initIndexView() throws Exception {
		object.perform(get("/"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(forwardedUrl("index"))
			.andExpect(view().name("index"))
			.andExpect(model().attribute("message", "Abandon hope all ye who enter here"));
	}
	
}
