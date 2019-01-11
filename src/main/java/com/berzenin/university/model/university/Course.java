package com.berzenin.university.model.university;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.berzenin.university.model.persons.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "subject", nullable = false)
	private String subject;
	
	@ManyToMany
//	@JoinColumn(name = "groups_id")
	private List<Excercise> excercises;
	
	@ManyToMany
	private List<Teacher> teacher;

}
