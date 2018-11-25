package com.berzenin.university.model.university;

import com.berzenin.university.model.persons.Student;
import lombok.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "groups")
public class Group implements Comparable<Group>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@Column(name = "name")
	private String name;

//	@OneToMany(targetEntity = Student.class, 
//			mappedBy = "groupId", 
//			fetch = FetchType.EAGER)
//	private List<Student> students;

	@Override
	public int compareTo(Group o) {
		return name.compareTo(o.name);
	}
}
