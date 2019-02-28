package com.berzenin.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = { "com.berzenin.university" },
excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.berzenin.university.web.restcontroller"))
public class UniversityWebServiceApplication extends SpringBootServletInitializer  {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UniversityWebServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UniversityWebServiceApplication.class, args);
	}
}
