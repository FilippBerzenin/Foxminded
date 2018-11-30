package com.berzenin.university.model.university;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.berzenin.university.model.persons.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;


@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name= "groups")
public class Group implements Comparable<Group>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	private String name;

	@OneToMany(
			targetEntity = Student.class,
			mappedBy = "group", 
			fetch = FetchType.EAGER)
	private List<Student> students;

	@Override
	public int compareTo(Group o) {
		return name.compareTo(o.name);
	}
}
