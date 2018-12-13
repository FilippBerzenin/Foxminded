package com.berzenin.university.model.persons;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.berzenin.university.model.university.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {
	
	public Student(String name, String surename) {
	super(name, surename);
}

	public Student(long id, String name, String surename) {
		super(id, name, surename);
	}

	public Student(long id, String name, String surename, Group group) {
		super(id, name, surename);
		this.group = group;
	}
	
	public Student(String name, String surename, Group group) {
		super(name, surename);
		this.group = group;
	}

	@ManyToOne
	@JoinColumn(name = "groups_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Group group;	

}
