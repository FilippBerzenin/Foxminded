package com.berzenin.university.dao;

import org.springframework.data.repository.CrudRepository;

import com.berzenin.university.model.university.Group;

public interface GroupsRepository extends CrudRepository<Group, Long> {
	
	Iterable<Group> findByName(String findGroup);
	
	

}
