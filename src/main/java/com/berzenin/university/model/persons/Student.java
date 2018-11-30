package com.berzenin.university.model.persons;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.berzenin.university.model.university.Group;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id")
	private Group group;
}
