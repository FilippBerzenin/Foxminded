package com.berzenin.university.web;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.MyException;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/groups")
public class GroupViewController {

	private final GroupRepository groupRepository;

	@RequestMapping(value="/show/all", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getGroupsList(Model model) {
		returnAllGroups(model);
		return "groups";
	}

	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String findGroupByName(@RequestParam String filter, Model model) {
 		if (filter != null && !filter.isEmpty()) {
			model.addAttribute("groupsList", Arrays.asList(groupRepository.findByName(filter)
					.orElseThrow(NotFoundException::new)));
		} else {
				returnAllGroups(model);
		}
		return "groups";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addNewGroup(@RequestParam String newGroupsName, Model model) {
		if (!groupRepository.findByName(newGroupsName).isPresent()) {
			groupRepository.saveAndFlush(Group.builder().name(newGroupsName).build());
		}
		returnAllGroups(model);
		return "groups";		
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteGroup(@PathVariable("id") Long id, Model model) {
		groupRepository.delete(returnGroupIfPresent(id));;
		returnAllGroups(model);
		return "groups";		
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String updateGroup(@PathVariable("id") Long id, @RequestParam(value="newGroupName") String newGroupName, Model model) {
		Group group = returnGroupIfPresent(id);
		group.setName(newGroupName);
		groupRepository.saveAndFlush(group);
		returnAllGroups(model);
		return "groups";		
	}
	
	private Model returnAllGroups(Model model) {
		return model.addAttribute("groupsList", groupRepository.findAll());
	}
	
	private Group returnGroupIfPresent(long id) {
		return groupRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}
}
