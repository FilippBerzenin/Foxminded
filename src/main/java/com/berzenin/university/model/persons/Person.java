package com.berzenin.university.model.persons;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode
public abstract class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	@NotNull
	@Size(min=1, max=50)
	@Column(name = "name", nullable = false)
	protected String name;

	@NotNull
	@Size(min=1, max=50)
	@Column(name = "surename", nullable = false)
	protected String surename;

	public Person(String name, String surename) {
		this.name = name;
		this.surename = surename;
	}
}
