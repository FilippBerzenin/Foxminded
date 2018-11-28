package com.berzenin.university.model.persons;

import java.io.Serializable;

import javax.persistence.Entity;

import com.berzenin.university.model.university.Group;

import lombok.*;

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

}
