package com.berzenin.university.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class UniversityWebServiceTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityWebServiceTestApplication.class, args);
	}
}
