package com.berzenin.university.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository groupRepository;
    
    @GetMapping(
			value = "/groups",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Group> getAll () {
		return groupRepository.findAll();
	}
    
    
	@PostMapping(
			value = "/groups",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	Group addGroups(@RequestBody Group group) {
		return groupRepository.save(group);
	}	
	
	@GetMapping(
			value = "/groups/{id}/students",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Student> students(@PathVariable("id") Long groupId){
    	return getGroupsById(groupId).getStudents();
	}
	
	@GetMapping(
			value = "/groups/{id}", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	Group getGroupsById(@PathVariable long id) {
		return returnGroupIfPresent(id);
	}
	
	@PutMapping(
			value = "/groups/{id}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	Group updateStudent(@RequestBody Group group, @PathVariable("id") long id) {
		Group groupForUpdate = returnGroupIfPresent(id);
		groupForUpdate.setName(group.getName());
		return groupRepository.save(groupForUpdate);
	}
	
	@DeleteMapping(value = "/groups/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	Group deleteGroupsByEntity (@PathVariable("id") long id) {
		Group group = returnGroupIfPresent(id);
		groupRepository.delete(group);
		return group;
	}
	
	private Group returnGroupIfPresent(long id) {
		return groupRepository.findById(id)
				.orElseThrow(NotFoundException::new);
	}
}
