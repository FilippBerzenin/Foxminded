package com.berzenin.university.model.university;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.berzenin.university.model.persons.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "teacher")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	private String subject;
	
	@ManyToMany(mappedBy = "courses")
	private Set<Exercise> exercises;
	
	@ManyToMany(mappedBy = "courses")
	private Set<Group> groups;
	
	@ManyToMany(mappedBy = "courses")
	private Set<Teacher> teacher;
}
