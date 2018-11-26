package com.berzenin.university.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.group.GroupService;

@RequestMapping("/groups")
@RestController
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping()
	public String getAll(Map<String, Object> model) {
		Iterable<Group> allGroups = groupService.getGroupsRepository().findAll();
		allGroups.forEach(System.out::println);
		model.put("allGroups", allGroups);
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
	
	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable long id) {
		System.out.println("delete: "+id);
		groupService.getGroupsRepository().deleteById(id);
		return "HTTP DELETE was called";
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> deleteArticle(@PathVariable("id") String id) {
//		System.out.println("delete: "+id);
//		groupService.getGroupsRepository().deleteById(Long.parseLong(id));
//	   return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	} 

}
