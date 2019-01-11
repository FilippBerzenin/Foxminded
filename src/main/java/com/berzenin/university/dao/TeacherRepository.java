package com.berzenin.university.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.berzenin.university.model.persons.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}
