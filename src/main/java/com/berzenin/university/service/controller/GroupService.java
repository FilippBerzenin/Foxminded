package com.berzenin.university.service.controller;

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
		if (groupRepository.findByName(newGroupsName).isPresent()) {
			return false;
		}
			save(Group.builder().name(newGroupsName).build());
			return true;
	}
	
	public List<Group> searchGroupsByName (String nameFoSearch) {
		return groupRepository.findByNameContaining(nameFoSearch);
	}
	
	public Group findById(Long id) {
		return groupRepository.findById(id)
		.orElseThrow(NotFoundException::new);
	}
	
	public Group save(Group group) {
		return groupRepository.save(group);
		
	}
	
	public Group updateName(long id, String newGroupName) {
		Group group = this.findById(id);
		group.setName(newGroupName);
		return this.save(group);
	}
	
	public void delete(Long id) {
			groupRepository.delete(findById(id));
	}
	
	public void delete(Group group) {
			groupRepository.delete(group);
		}
}
