package com.berzenin.university.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.berzenin.university.service.controller.GroupServiceTest;
import com.berzenin.university.service.controller.StudentServiceTest;
import com.berzenin.university.web.mvc.GroupViewIntegrationTest;
import com.berzenin.university.web.mvc.IndexIntegrationTest;
import com.berzenin.university.web.mvc.StudentViewIntegratinTest;
import com.berzenin.university.web.rest.GroupRestEndpointIntegrationTest;
import com.berzenin.university.web.rest.StudentRestEndpointIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
		GroupServiceTest.class, 
		StudentServiceTest.class, 
		GroupViewIntegrationTest.class,
		IndexIntegrationTest.class, 
		StudentViewIntegratinTest.class, 
		GroupRestEndpointIntegrationTest.class,
		StudentRestEndpointIntegrationTest.class })
public class RunAllTest {

}
