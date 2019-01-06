package com.berzenin.university.service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	public String delete(Long id) {
		try {
			groupRepository.delete(findById(id));
			return id+ " Successfully deleted.";
		}catch (RuntimeException e) {
			log.info("Error with delete" + e);
			return id+ " Dosn't deleted.";
		}
	}
	
	public String delete(Group group) {
		try {
			groupRepository.delete(group);
			return group.getId()+ " Successfully deleted.";
		} catch (RuntimeException e) {
			log.info("Error with delete" + e);
			return group.getId()+ " Dosn't deleted.";
		}
	}
	
}
