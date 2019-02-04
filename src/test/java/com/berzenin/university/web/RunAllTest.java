package com.berzenin.university.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.berzenin.university.service.controller.GroupServiceTest;
import com.berzenin.university.service.controller.StudentServiceTest;
import com.berzenin.university.web.controller.ExcerciseViewController;
import com.berzenin.university.web.mvc.CourseViewIntegrationTest;
import com.berzenin.university.web.mvc.GroupViewIntegrationTest;
import com.berzenin.university.web.mvc.IndexIntegrationTest;
import com.berzenin.university.web.mvc.StudentViewIntegratinTest;
import com.berzenin.university.web.mvc.TeacherViewintegrationTest;
import com.berzenin.university.web.rest.CourseRestEndPointIntegrationTest;
import com.berzenin.university.web.rest.ExerciseRestEndPointIntegrationTest;
import com.berzenin.university.web.rest.GroupRestEndpointIntegrationTest;
import com.berzenin.university.web.rest.StudentRestEndpointIntegrationTest;
import com.berzenin.university.web.rest.TeacherRestEndPointIntegrationTest;
import com.berzenin.university.web.rest.TimetableRestEndPointIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
		GroupServiceTest.class, 
		StudentServiceTest.class, 
		GroupViewIntegrationTest.class,
		IndexIntegrationTest.class, 
		StudentViewIntegratinTest.class,
		TeacherViewintegrationTest.class,
		ExcerciseViewController.class,
		CourseViewIntegrationTest.class,
		GroupRestEndpointIntegrationTest.class,
		StudentRestEndpointIntegrationTest.class,
		TeacherRestEndPointIntegrationTest.class,
		CourseRestEndPointIntegrationTest.class,
		ExerciseRestEndPointIntegrationTest.class,
		TimetableRestEndPointIntegrationTest.class
		})
public class RunAllTest {

}
