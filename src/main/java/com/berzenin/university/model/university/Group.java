package com.berzenin.university.model.university;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.berzenin.university.model.persons.Student;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "students")
//@Data
//@Builder
@Entity
@AllArgsConstructor
@Table(name= "groups")
@EqualsAndHashCode(exclude = "students")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
