package com.berzenin.university.model.persons;

import java.time.LocalDate;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.berzenin.university.model.enums.Gender;

@StaticMetamodel(Person.class)
public class Person_ {
	
	public static volatile SingularAttribute<Student, Long> id;
	public static volatile SingularAttribute<Student, String> name;
	public static volatile SingularAttribute<Student, String> surename;
	public static volatile SingularAttribute<Student, LocalDate> dateOfBirth;
	public static volatile SingularAttribute<Student, Gender> gender;

}
