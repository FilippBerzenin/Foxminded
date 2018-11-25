package com.berzenin.university.model.persons;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;

@StaticMetamodel(Student.class)
public class Student_ extends Person_{

	public static volatile SingularAttribute<Student, Group> groupId;

}
