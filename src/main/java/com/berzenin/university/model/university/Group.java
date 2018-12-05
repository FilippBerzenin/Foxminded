package com.berzenin.university.model.university;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.berzenin.university.model.persons.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name= "groups")
@EqualsAndHashCode(exclude = "students")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	private String name;

	@OneToMany(
			targetEntity = Student.class,
			mappedBy = "group")
	private List<Student> students;

	public Group(String name) {
		this.name = name;
	}
}
