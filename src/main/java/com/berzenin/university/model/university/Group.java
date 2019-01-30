package com.berzenin.university.model.university;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.berzenin.university.model.persons.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "groups")
public class Group {


	@Id
	@GeneratedValue
	private long id;

	@NonNull
	@Size(min=1, max=150)
	private String name;

	@OneToMany(mappedBy = "group")
	private Set<Student> students;
	
	@ManyToMany
	@JoinTable(
			name = "group_course", 
			joinColumns = { @JoinColumn(name = "group_id") }, 
			inverseJoinColumns = {@JoinColumn(name = "course_id") })
		@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Course> courses;

	public Group(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Group(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
	}
	
	
}
