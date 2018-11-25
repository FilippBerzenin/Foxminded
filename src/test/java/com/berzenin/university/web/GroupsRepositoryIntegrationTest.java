package com.berzenin.university.web;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.group.GroupService;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
public class GroupsRepositoryIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
//	@Autowired	
//	private WebClient webClient;
	
	@MockBean
	private GroupService groupService;
	
	private List<Group> groups = Arrays.asList(new Group(1, "test"), new Group(2, "test2"));
	
//	@Before
//	public void init() {
//		when(groupService.getGroupsRepository().findAll()).thenReturn(groups);
//		webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc)
//				.useMockMvcForHosts("univer.com", "test.ua")
//				.build();
//	}
	
	@Test 
	public void requestForHtnlPageTitle () throws Exception {
		this.mockMvc.perform(get("/groups")
				.accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(content().string(
						containsString("test")
						));
	}

}
