package com.berzenin.university.model.persons;

import java.util.List;

import com.berzenin.university.model.university.Course;

import lombok.Data;

@Data
public class Teacher extends Person {
	
	private List<Course> courses;

}
