package com.berzenin.university.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.berzenin.university.model.university.Exercise;

@Repository
public interface ExcerciseRepository extends CrudRepository<Exercise, Long> {

}
