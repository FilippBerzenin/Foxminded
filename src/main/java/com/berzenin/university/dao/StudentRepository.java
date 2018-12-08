package com.berzenin.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.berzenin.university.model.persons.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
