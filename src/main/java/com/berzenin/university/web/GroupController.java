package com.berzenin.university.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.group.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping()
	public String getAll(Map<String, Object> model) {
		Iterable<Group> allGroups = groupService.getGroupsRepository().findAll();
		model.put("groups", allGroups);
		return "groups";		
	}
	
	@PostMapping()
	public String addNewItem (@RequestParam String newGroup, Map<String, Object> model) {
		Group group = Group.builder().name(newGroup).build();
		groupService.getGroupsRepository().save(group);
		Iterable<Group> allGroups = groupService.getGroupsRepository().findAll();
		model.put("groups", allGroups);
		return "groups";		
	}
	
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable long id) {
//		System.out.println("delete: "+id);
//		groupsRepository.deleteById(id);
//	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") String id) {
		System.out.println("delete: "+id);
		groupService.getGroupsRepository().deleteById(Long.parseLong(id));
	   return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	} 

}
