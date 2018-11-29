package com.berzenin.university.web;

import static org.assertj.core.api.Assertions.allOf;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupControllerTest {

	/*TODO: Протестировать каждый endpoint и каждый HTTP метод */
	
//	@Autowired /*TODO: Why do we need this here?*/
//	private GroupController groupController;
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Sql(scripts = "/sqlInject/web/groupControllerSql.sql")
	@Test
	public void getAllTest() throws Exception {
		this.mockMvc.perform(get("/groups")
				.accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string(allOf(
						containsString("first"),
						containsString("second")
						)));
	}

	@Sql(scripts = "/sqlInject/web/groupControllerSql.sql")
	@Test
	public void addNewItemTest() throws Exception {
		this.mockMvc.perform(post("/groups")
				.accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(content().string(allOf(
						containsString("first"),
						containsString("second")
						)));
	}
/*FIXME*/
}
