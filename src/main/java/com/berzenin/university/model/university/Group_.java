package com.berzenin.university.model.university;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.berzenin.university.model.persons.Student;

@StaticMetamodel(Group.class)
public class Group_ {
    public static volatile SingularAttribute<Group, Long> id;
    public static volatile SingularAttribute<Group, Student> students;
    public static volatile SingularAttribute<Group, String> name;

}
