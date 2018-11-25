package com.berzenin.university.model.persons;

import java.io.Serializable;

import javax.persistence.Entity;

import com.berzenin.university.model.university.Group;

import lombok.*;

//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Entity
public class Student extends Person implements Serializable {

	@Override
	public int compareTo(Person arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

//	private static final long serialVersionUID = 1L;
//
//	private Group groupId;
}
