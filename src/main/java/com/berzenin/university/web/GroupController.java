package com.berzenin.university.web;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository groupRepository;

    @GetMapping(
			value = "groups/all",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Group> getAll () {
		return groupRepository.findAll();
	}

	@GetMapping(
			value = "/groups/{id}/students",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Student> students(@PathVariable("id") Long groupId){
    	return null;
	}
}
