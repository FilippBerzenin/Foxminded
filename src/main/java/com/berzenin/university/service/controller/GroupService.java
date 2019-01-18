package com.berzenin.university.service.controller;

import java.util.List;

import org.springframework.stereotype.Service;

import com.berzenin.university.dao.GroupRepository;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.web.exception.NotFoundException;

@Service
public class GroupService extends GenericServiceImpl<Group, GroupRepository> {

	public GroupService(GroupRepository repository) {
		super(repository);
	}

	public boolean addNewGroup(String newGroupsName) {
		if (repository.findByName(newGroupsName).isPresent()) {
			throw new NotFoundException();
		}
			this.saveOrUpdate(Group.builder().name(newGroupsName).build());
			return true;
	}
	
	public List<Group> searchGroupsByName (String nameFoSearch) {
		return repository.findByNameContaining(nameFoSearch);
	}

	public Group updateName(long id, String newGroupName) {
		Group group = this.findById(id);
		group.setName(newGroupName);
		return this.saveOrUpdate(group);
	}
}
