package com.berzenin.university.service.student;

import lombok.*;

@Builder
@Getter
@Setter
public class StudentView {
	
	private long id;
	private String name;
	private String surename;
	private String dateOfBirth;
	private String gender;
	private String groupId;
	private String newGroupsName;
}
