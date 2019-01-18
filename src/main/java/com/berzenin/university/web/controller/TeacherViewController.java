package com.berzenin.university.web.controller;

import com.berzenin.university.model.persons.Teacher;
import com.berzenin.university.service.controller.GroupService;
import com.berzenin.university.service.controller.TeacherService;

public class TeacherViewController extends GenericViewControllerImpl<Teacher, TeacherService> {
	
	public 	TeacherViewController(GroupService service) {
		page = "teachers";
	}
}
