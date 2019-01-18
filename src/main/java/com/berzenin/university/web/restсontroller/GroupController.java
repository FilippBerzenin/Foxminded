package com.berzenin.university.web.restсontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.controller.GroupService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequiredArgsConstructor
@RequestMapping(value="/api/groups")
public class GroupController extends GenericControllerImpl<Group, GroupService> {
	
	public GroupController(GroupService service) {
		super(service);
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	Group addGroups(@RequestBody Group newGroup) {
		return service.saveOrUpdate(newGroup);
	}
	
	@GetMapping(
			value = "/{id}/students",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Student> students(@PathVariable("id") long groupId){
    	return getGroupsById(groupId).getStudents();
	}
	
	@GetMapping(
			value = "/{id}", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	Group getGroupsById(@PathVariable("id") long id) {
		return returnGroupIfPresent(id);
	}
	
	@PutMapping(
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	Group updateStudent(@RequestBody Group group, @PathVariable("id") long id) {
		Group groupForUpdate = returnGroupIfPresent(id);
		groupForUpdate.setName(group.getName());
		return service.saveOrUpdate(groupForUpdate);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	Group deleteGroupsByEntity (@PathVariable("id") long id) {
		Group group = returnGroupIfPresent(id);
		service.remove	(group);
		return group;
	}
	
	private Group returnGroupIfPresent(long id) {
		return service.findById(id);
	}
}
