package com.berzenin.university.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.berzenin.university.model.university.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
