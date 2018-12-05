package com.berzenin.university.dao;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	
	List<Student> findAllStudentByGroupId(Long id);

}
