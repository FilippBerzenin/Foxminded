package com.berzenin.university.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.berzenin.university.model.university.Group;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityWebServiceTestApplication.class)
@AutoConfigureMockMvc
public class GroupViewIntegratinTest {

	@Autowired
	MockMvc subject;

	@MockBean
	GroupRepository groupRepository;

	@MockBean
	StudentRepository studentRepository;

	@Test
	public void getGroupsListTest() throws Exception {
		// Given
		when(groupRepository.findAll()).thenReturn(Arrays.asList(new Group(1, "first"), new Group(2, "second")));
		// Then
		subject.perform(get("/groups/show/all"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("groups"))
			.andExpect(view().name("groups"))
			.andExpect(model().attribute("groupsList", hasSize(2)))
			.andExpect(model().attributeExists("groupsList"));
		// When
		verify(groupRepository).findAll();

		List<Group> groups = groupRepository.findAll();
		assertThat(groups.get(0).getId(), is(1L));
		assertThat(groups.get(1).getId(), is(2L));
		assertThat(groups.get(0).getName(), is("first"));
		assertThat(groups.get(1).getName(), is("second"));
	}

	@Test
	public void addNewGroupTest() throws Exception {
		// Given
		String newGroupsName = "First";
		Group groupForAdd = new Group(newGroupsName);
		when(groupRepository.save(any())).thenReturn(groupForAdd);
		// Then
		subject.perform(post("/groups/create")
			.param("newGroupsName", newGroupsName))
			.andDo(print())
			.andExpect(forwardedUrl("groups"))
			.andExpect(view().name("groups"))
			.andExpect(status().isCreated());
		// When
		verify(groupRepository).saveAndFlush(groupForAdd);
	}

	@Test
	public void deleteById() throws Exception {
		// Given
		Long id = 1L;
		Group groupsForDelete = new Group(id, "test", null);
		when(groupRepository.findById(id)).thenReturn(Optional.of(groupsForDelete));
		// Then
		subject.perform(get("/groups/delete/{id}", id))
			.andExpect(view().name("groups"))
			.andDo(print())
			.andExpect(status().isNoContent());
		// When
		verify(groupRepository).findById(id);
	}

	@Test
	public void updateGroupTest() throws Exception {
		// Given
		Long id = 1L;
		String newName = "First";
		Group groupForUpdate = new Group(id, "First", null);
		Group groupWithOldParam = new Group(id, "Fir", null);
		when(groupRepository.findById(id)).thenReturn(Optional.of(groupWithOldParam));
		when(groupRepository.save(groupForUpdate)).thenReturn(groupForUpdate);
		// Then
		subject.perform(post("/groups/update/{id}", id).param("newGroupName", newName)).andDo(print())
				.andExpect(forwardedUrl("groups")).andExpect(view().name("groups")).andExpect(status().isOk());
		// When
		verify(groupRepository).findById(id);
		verify(groupRepository).saveAndFlush(new Group(id, "First", null));
	}

}
