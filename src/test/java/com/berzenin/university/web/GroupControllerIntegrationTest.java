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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.berzenin.university.dao.GroupsRepository;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.group.GroupService;

import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
public class GroupControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GroupService groupService;
	
	@MockBean
	private GroupsRepository groupsRepository;
	
	private List<Group> allGroups = Arrays.asList(new Group(1, "first"), new Group(2, "second"));
	
	@Before
	public void init() {
		when(groupsRepository.findAll()).thenReturn(allGroups);
		when(groupService.getGroupsRepository().findAll()).thenReturn(groupsRepository.findAll());
		groupService.getGroupsRepository().findAll().forEach(System.out::println);
//		webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc)
//				.useMockMvcForHosts("univer.com")
//				.build();
	}
	
	@SneakyThrows
	@Test
	public void groupControllerFindAllGroup () {
		this.mockMvc.perform(get("/groups").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(content().string(allOf(
				containsString("first"),
				containsString("second")
				)));
	}

}
