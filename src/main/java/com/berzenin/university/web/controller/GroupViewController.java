package com.berzenin.university.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.controller.GroupService;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/groups")
public class GroupViewController {

	private final GroupService groupService;

	@RequestMapping(value="/show/all", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getGroupsList(Model model) {
		setGroupsToModel(model);
		return "groups";
	}

	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String findGroupByName(@RequestParam String filter, Model model) {
		try {
			model.addAttribute("groupsList", groupService.searchGroupsByName(filter));
			return "groups";	
		} catch (NotFoundException e) {
			return "error";
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addNewGroup(@RequestParam String newGroupsName, Model model) {
		groupService.addNewGroup(newGroupsName);
		setGroupsToModel(model);
		return "groups";		
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteGroup(@PathVariable("id") Long id, Model model) {
		groupService.delete(id);
		setGroupsToModel(model);
		return "groups";		
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String updateGroup(@PathVariable("id") Long id, @RequestParam(value="newGroupName") String newGroupName, Model model) {
		Group group = groupService.findById(id);
		group.setName(newGroupName);
		groupService.save(group);
		setGroupsToModel(model);
		return "groups";		
	}
	
	private void setGroupsToModel(Model model) {
		model.addAttribute("groupsList", groupService.findAll());
	}
}
