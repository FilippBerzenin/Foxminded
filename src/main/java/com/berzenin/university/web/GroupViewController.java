package com.berzenin.university.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.model.university.Group;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/group_")
public class GroupViewController {

	private final GroupRepository groupRepository;

	@RequestMapping(method=RequestMethod.GET)
	public String getGroupsList(Model model) {
		returnAllGroups(model);
		return "groups";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addNewGroup(@RequestParam String newGroupsName, Model model) {
		groupRepository.saveAndFlush(Group.builder().name(newGroupsName).build());
		returnAllGroups(model);
		return "groups";		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String deleteGroup(@PathVariable("id") Long id, Model model) {
		groupRepository.deleteById(id);
		returnAllGroups(model);
		return "groups";		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateGroup(@PathVariable("id") Long id, @RequestParam String newGroupName, Model model) {
		System.out.println("start");
		Group group = groupRepository.findById(id).get();
		group.setName(newGroupName);
		groupRepository.saveAndFlush(group);
		returnAllGroups(model);
		return "groups";		
	}
	
	private Model returnAllGroups(Model model) {
		return model.addAttribute("groupsList", groupRepository.findAll());
	}

}
