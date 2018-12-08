package com.berzenin.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.berzenin.university.model.university.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
