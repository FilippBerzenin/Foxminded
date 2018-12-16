package com.berzenin.university.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
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
import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.university.Group;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = UniversityWebServiceTestApplication.class)
@AutoConfigureMockMvc
public class GroupViewIntegratinTest {

	
	@Autowired
	MockMvc subject;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	GroupRepository groupRepository;

	@MockBean
	StudentRepository studentRepository;	
	
	@Test
	public void getGroupsListTest () throws Exception {
		when(groupRepository.findAll()).thenReturn(Arrays
				.asList(new Group(1, "first"), 
						new Group(2, "second")));
		subject.perform(get("/groups/show/all"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("groups"))
		.andExpect(view().name("groups"))
		.andExpect(model().attribute("groupsList", hasSize(2)))
		.andExpect(model().attributeExists("groupsList"));		
		
		List<Group> groups = groupRepository.findAll();		
		assertThat(groups.get(0).getId(), is(1L));
		assertThat(groups.get(1).getId(), is(2L));
		assertThat(groups.get(0).getName(), is("first"));
		assertThat(groups.get(1).getName(), is("second"));
	}
	
	//TODO
//    @Test
    public void findByIdWhenGroupIsNotFound() throws Exception {
    	subject.perform(get("/show/{id}", 3L))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/404"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/error/404.jsp"));
    }
	
	//TODO
//    @Test
	public void addNewGroupTest()throws Exception  {
		
	}
	
    @Test
    public void deleteById() throws Exception {
		Long id = 1L;
		Group groupsForDelete = new Group(id, "test", null);
		when(groupRepository.findById(id)).thenReturn(Optional.of(groupsForDelete));
		
    	subject.perform(get("/groups/delete/{id}", 1L))
                .andExpect(view().name("groups"))
				.andDo(print())
				.andExpect(status().isNoContent());
    }

}
