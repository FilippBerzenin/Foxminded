package com.berzenin.university.model.persons;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.berzenin.university.model.university.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "courses")
public class Teacher extends Person {
	
	

	public Teacher(long id, String name, String surename, Set<Course> courses) {
		super(id, name, surename);
		this.courses=courses;
	}

	public Teacher(String name, String surename) {
		super(name, surename);
		// TODO Auto-generated constructor stub
	}

	@ManyToMany
	@JoinTable(
		name = "teacher_courses", 
		joinColumns = { @JoinColumn(name = "teacher_id") }, 
		inverseJoinColumns = {@JoinColumn(name = "courses_id") })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Course> courses;
}
