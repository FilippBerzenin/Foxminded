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
	
	final GroupRepository groupRepository;
	
	public List<Group> findAll() {
		return groupRepository.findAll();
	}
	
	public boolean addNewGroup(String newGroupsName) {
		if (!groupRepository.findByName(newGroupsName).isPresent()) {
			groupRepository.saveAndFlush(Group.builder().name(newGroupsName).build());
			return true;
		}
		return false;
	}
	
	public List<Group> searchGroupByName (String nameFoSearch) {
 		if (nameFoSearch != null && !nameFoSearch.isEmpty()) {
			 return Arrays.asList(groupRepository.findByName(nameFoSearch)
					.orElseThrow(NotFoundException::new));
		} else {
				return findAll();
		}
	}
	
	public Group findById(Long id) {
		return groupRepository.findById(id)
		.orElseThrow(NotFoundException::new);
	}
	
	public Group save(Group group) {
		return groupRepository.saveAndFlush(group);
		
	}
	
	public void delete(Long id) {
		groupRepository.delete(returnGroupIfPresent(id));
	}
	
	
	public Group returnGroupIfPresent(Long id) {
		return groupRepository.findById(id).get();
	}

}
