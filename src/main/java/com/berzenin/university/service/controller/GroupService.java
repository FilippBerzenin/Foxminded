package com.berzenin.university.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {
	
	private final GroupRepository groupRepository;
	
	public List<Group> findAll() {
		return groupRepository.findAll();
	}
	
	public boolean addNewGroup(String newGroupsName) {
		if (groupRepository.findByName(newGroupsName)
				.orElse(new Group("Empty"))
				.getName().equals("Empty")) {
			save(Group.builder().name(newGroupsName).build());
			return true;
		}
		return false;
	}
	
	public List<Group> searchGroupsByName (String nameFoSearch) {
			 return Arrays.asList(groupRepository.findByName(nameFoSearch)
					.orElseThrow(NotFoundException::new));
	}
	
	public Group findById(Long id) {
		return groupRepository.findById(id)
		.orElseThrow(NotFoundException::new);
	}
	
	public Group save(Group group) {
		return groupRepository.save(group);
		
	}
	
	public void delete(Long id) {
			groupRepository.delete(findById(id));
	}
	
	public void delete(Group group) {
			groupRepository.delete(group);
		}
}
