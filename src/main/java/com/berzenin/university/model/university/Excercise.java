package com.berzenin.university.model.university;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Excercise {
	
	@Id
	private Long id;
	
	@NotNull
	private String name;

	@NotNull
	private DayOfWeek dayOfWeek;
	
	@NotNull
	private LocalTime timeBegin;
	
	@NotNull
	private LocalTime timeFinish;
	
	@ManyToMany
	private List<Course> course;
	
}
