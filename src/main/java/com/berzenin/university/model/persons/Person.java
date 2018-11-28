package com.berzenin.university.model.persons;

import com.berzenin.university.model.enums.Gender;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Person {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surename", nullable = false)
	private String surename;

	public Person() {

	}

	public Person(long id, String name, String surename) {
		this.id = id;
		this.name = name;
		this.surename = surename;
	}
}
