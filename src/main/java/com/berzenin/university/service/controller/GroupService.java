package com.berzenin.university.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.NotFoundException;

@Service
public class GroupService extends GenericServiceImpl<Group, GroupRepository> {

	@Autowired
	public GroupService(GroupRepository repository) {
		super(repository);
	}

	public boolean addNewGroup(String newGroupsName) {
		if (repository.findByName(newGroupsName).isPresent()) {
			return false;
		}
			save(Group.builder().name(newGroupsName).build());
			return true;
	}
	
	public List<Group> searchGroupsByName (String nameFoSearch) {
		return repository.findByNameContaining(nameFoSearch);
	}
	
	public Group findById(Long id) {
		return repository.findById(id)
		.orElseThrow(NotFoundException::new);
	}
	
	public Group save(Group group) {
		return repository.save(group);
		
	}
	
	public Group updateName(long id, String newGroupName) {
		Group group = this.findById(id);
		group.setName(newGroupName);
		return this.save(group);
	}
	
	public void delete(Long id) {
			repository.delete(findById(id));
	}
	
	public void delete(Group group) {
			repository.delete(group);
		}
}
