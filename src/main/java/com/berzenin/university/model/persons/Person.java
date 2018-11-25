package com.berzenin.university.model.persons;

import com.berzenin.university.model.enums.Gender;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString()
//@EqualsAndHashCode(exclude = {"id"})
//@MappedSuperclass
public abstract class Person implements Comparable<Person>, Serializable {

	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	protected long id;
//
//	@Column(name = "name", nullable = false)
//	protected String name;
//
//	@Column(name = "surename", nullable = false)
//	protected String surename;
//
//	@Column(name = "date_of_birth", nullable = false)
//	protected LocalDate dateOfBirth;
//
//	@Enumerated(EnumType.STRING)
//	@Column(name = "gender", nullable = false)
//	protected Gender gender;
//
//	@Override
//	public int compareTo(Person o) {
//		return surename.compareTo(o.surename);
//	}
}
