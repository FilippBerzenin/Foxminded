package com.berzenin.university.model.persons;

import com.berzenin.university.model.university.Group;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {
	
	public Student() {
		super();
	}
	public Student(String name, String surename) {
		super(0, name, surename);
	}

	public Student(long id, String name, String surename) {
		super(id, name, surename);
	}

	public Student(long id, String name, String surename, Group group) {
		super(id, name, surename);
		this.group = group;
	}

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
}
