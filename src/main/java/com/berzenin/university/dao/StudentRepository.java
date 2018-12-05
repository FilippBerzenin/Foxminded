package com.berzenin.university.dao;

import com.berzenin.university.model.persons.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findAllByGroup(Long group_id);
	
}
