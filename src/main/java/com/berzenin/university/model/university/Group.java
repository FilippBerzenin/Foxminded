package com.berzenin.university.model.university;

import com.berzenin.university.model.persons.Student;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name= "groups")
@EqualsAndHashCode(exclude = "students")
public class Group  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	private String name;

	@OneToMany(
			targetEntity = Student.class,
			mappedBy = "group")
	private List<Student> students;

	public Group(long id, String name) {
		this.id = id;
		this.name = name;
	}
}
