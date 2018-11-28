package com.berzenin.university.web;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.group.GroupService;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
 	
	@GetMapping("/groups")
	public String getAll(Map<String, Object> model) {
		Iterable<Group> allGroups = groupService.getGroupsRepository().findAll();
		model.put("groups", allGroups);
		return "groups";
	}
	
	@PostMapping()
	public String addNewItem (@RequestParam("newGroup") String newGroup, Map<String, Object> model) {
		Group group = Group.builder().name(newGroup).build();
		groupService.getGroupsRepository().save(group);
		Iterable<Group> allGroups = groupService.getGroupsRepository().findAll();
		model.put("groups", allGroups);
		return "groups";		
	}
	
	@PostMapping("filter")
	public String filter(@RequestParam String findGroup, Map<String, Object> model) {
		Iterable<Group> allGroups = groupService.getGroupsRepository().findByName(findGroup);
		model.put("groups", allGroups);
		return "index";
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam String id, Map<String, Object> model) {
		groupService.getGroupsRepository().deleteById(Long.parseLong(id));
		return getAll(model);
	}
	
	@PostMapping("update")
	public String update(@RequestParam String id, @RequestParam String newNameOfGroup, Map<String, Object> model) {
		Optional<Group> group;
		group = groupService.getGroupsRepository().findById(Long.parseLong(id));
		group.get().setName(newNameOfGroup);		
		groupService.getGroupsRepository().save(group.get());
		return getAll(model);		
	}
}
