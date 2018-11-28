package com.berzenin.university.web;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupControllerSystemTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Sql(scripts = "/sqlInject/web/insert-values-groups.sql")
	@Sql(scripts = "/sqlInject/web/clean-table-group.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@SneakyThrows
	@Test
	public void getAllTest() {
		this.mockMvc.perform(get("/groups").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(content().string(allOf(containsString("first"), containsString("second"))));
	}

	@SneakyThrows
	@Test
	public void addNewItemTest() {
		String groupName = "test";
		this.mockMvc
				.perform(post("").param("newGroup", groupName)
						.accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.view().name("groups"))
				.andExpect(model().size(1)).andExpect(model().attributeExists("groups"))
				.andExpect(content().string(containsString(groupName)));
	}
}
