package com.berzenin.university.service.student;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
//import berzenin.persistence.dao.exception.DaoException;
//import berzenin.persistence.dao.jpa.JpaGroupsDao;
//import berzenin.persistence.dao.jpa.JpaStudentsDao;
import com.berzenin.university.service.WebService;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
public class StudentManagementService implements WebService<StudentView, Student> {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public boolean deleteById(long itemsId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewItem(Student items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<StudentView> getAllandSendForServlet(long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Student update) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StudentView toView(Student model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isItemsPresent(Student items) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	private JpaStudentsDao jpaStudentsDao;
//	private JpaGroupsDao jpaGroupsDao;

//	public StudentManagementService(JpaStudentsDao jpaStudentsDao, JpaGroupsDao jpaGroupsDao) {
//		this.jpaGroupsDao = jpaGroupsDao;
//		this.jpaStudentsDao = jpaStudentsDao;
//	}
//
//	@Override
//	public Collection<StudentView> getAllandSendForServlet(long groupId) {
//		return convertorStudentFromStudentView(jpaGroupsDao.getStudentsFromGroup(jpaGroupsDao.getItemById(groupId).get()));
//	}
//
//	@Override
//	public boolean createNewItem(Student student) {
//		try {
//			student.setGroupId(jpaGroupsDao.getItemsByName(student.getGroupId().getName()).get(0));
//			if (!isItemsPresent(student) && jpaStudentsDao.add(student)) {
//				log.info(student.getId() + " is successfully added");
//				return true;
//			}
//		} catch (DaoException ex) {
//			log.error("DAO exception, error not add new student", ex);
//		}
//		return false;
//	}
//	
//	@Override
//	public boolean update(Student student) { // do it		
//		try {			
//			Group newGroup = null;
//			List<Group> groups = jpaGroupsDao.getItemsByName(student.getGroupId().getName());
//			if (groups != null && groups.size()>0){
//				newGroup = groups.get(0);
//			} else return false;
//			student.setGroupId(newGroup);
//			if (jpaStudentsDao.update(student)) {
//				log.info(student.getId()+" successfully updated");
//				return true;
//			}
//		} catch (DaoException ex) {
//			log.error("DAO exception update student", ex);
//		}
//		return false;
//	}
//
//	@Override
//	public boolean isItemsPresent(Student student) {
//		List<Student> isItemsPresent = jpaStudentsDao.getItemByObject(student);
//		if (isItemsPresent != null && isItemsPresent.size()>0) {
//			return true;
//		}
//		else return false;
//	}
//
//	public boolean deleteById(long itemsId) {
//		try {
//			jpaStudentsDao.delete(jpaStudentsDao.getItemById(itemsId).get());
//			log.error("student delete successfully");
//			return true;
//		} catch (DaoException ex) {
//			log.error("DAO exception delete student", ex);
//		}
//		return false;
//	}
//
//	private Collection<StudentView> convertorStudentFromStudentView(Collection<Student> collection) {
//		return collection
//				.stream()
//				.sorted()
//				.map(this::toView).collect(toList());
//	}
//
//	public StudentView toView(Student model) {
//		return StudentView.builder()
//				.id(model.getId())
//				.name(model.getName())
//				.surename(model.getSurename())
//				.dateOfBirth(model.getDateOfBirth().format(formatter))
//				.gender(model.getGender().toString())
//				.groupId(model.getGroupId().toString())
//				.build();
//	}
}
