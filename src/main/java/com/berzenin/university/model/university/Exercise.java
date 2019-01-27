package com.berzenin.university.model.university;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "courses")
public class Exercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;	
	
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	
	@NotNull
	@DateTimeFormat(pattern = "HH-mm")
	private LocalTime timeBegin;
	
	@NotNull
	@DateTimeFormat(pattern = "HH-mm")
	private LocalTime timeFinish;
	
	@ManyToMany
	@JoinTable(
		name = "exercise_course", 
		joinColumns = { @JoinColumn(name = "exercise_id") }, 
		inverseJoinColumns = {@JoinColumn(name = "course_id") })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Course> courses;
	
}
