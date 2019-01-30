package com.berzenin.university.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.berzenin.university.model.university.Exercise;

@Repository
public interface ExcerciseRepository extends CrudRepository<Exercise, Long> {
	
//	@Query("select exercise0_.id as id1_1_, exercise0_.date as date2_1_, exercise0_.name as name3_1_, exercise0_.time_begin as time_beg4_1_, exercise0_.time_finish as time_fin5_1_ from exercise exercise0_ left outer join exercise_course courses1_ on exercise0_.id=courses1_.exercise_id left outer join course course2_ on courses1_.course_id=course2_.id left outer join group_course groups3_ on course2_.id=groups3_.course_id left outer join groups group4_ on groups3_.group_id=group4_.id left outer join group_student students5_ on group4_.id=students5_.group_id left outer join student student6_ on students5_.student_id=student6_.id left outer join group_student student6_1_ on student6_.id=student6_1_.student_id where student6_.surename=? and exercise0_.name=?")
//	public List<Exercise> findAllExercisesBetweenDatesForStudent();
	
	public List<Exercise> findByCourses_Id(Long id);
	public List<Exercise> findByCourses_Groups_Id(Long Id);
	public List<Exercise> findByCourses_Groups_Name(String name);
	public List<Exercise> findByCourses_Groups_Students_IdAndDateBetween(
			Long id, 
			LocalDate publicationTimeStart,
			LocalDate publicationTimeEnd);
	
	public List<Exercise> findByCourses_Groups_Students_Surename(
			@Param("surename") String surename);

}
