package com.berzenin.university.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.berzenin.university.model.persons.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	
	// TODO
	@Query(
			  value = "SELECT * FROM student s WHERE s.groups_id = ?1", 
			  nativeQuery = true)
	List<Student> findAllStudentsByGroup(Long id);
	
	Optional<Student> findByName(String name);
	
	Optional<Student> findBySurename(String surename);
	
}
