package com.berzenin.university.service.group;

import com.berzenin.university.model.persons.Student;
import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.WebService;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
public class GroupManagementService implements com.berzenin.university.service.WebService<GroupsView, Group> {@Override
	public boolean deleteById(long itemsId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewItem(Group items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<GroupsView> getAllandSendForServlet(long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Group update) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GroupsView toView(Group model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isItemsPresent(Group items) {
		// TODO Auto-generated method stub
		return false;
	}
//
//	private DAO<Group> jpaGroupsDao;
//
//	public GroupManagementService(DAO<Group> jpaGroupsDao) {
//		this.jpaGroupsDao = jpaGroupsDao;
//	}
//	
//	public List<Student> getStudentsFromGroup (Group group) {
//		return group.getStudents();
//	}
//
//	@Override
//	public boolean deleteById(long groupId) {
//		Group groupForDelete = jpaGroupsDao.getItemById(groupId).get();		
//		if (groupForDelete.getStudents().size()==0 && jpaGroupsDao.delete(groupForDelete)) {
//			log.info(groupId + " has been successfully delete.");
//			return true;
//		}
//		else log.info(groupId + " delete - error. Maybe group has some students.");
//		return false;
//	}
//
//	@Override
//	public boolean createNewItem(Group group) {
//			if (!isItemsPresent(group) && jpaGroupsDao.add(group)) {
//				log.info(group.getName() + " has been successfully created.");
//				return true;
//			}
//			log.info(group + " create - error. Maybe the group name is already in the database.");
//			return false;
//	}
//
//	@Override
//	public boolean isItemsPresent(Group items) {
//		List<Group> isItemsPresent = jpaGroupsDao.getItemsByName(items.getName());
//		if (isItemsPresent != null && isItemsPresent.size()>0) {
//			return true;
//		}
//		else return false;
//	}
//	
//	@Override
//	public Collection<GroupsView> getAllandSendForServlet(long groupId) {
//		return convertorFromView(jpaGroupsDao.getAll());
//	}
//
//	@Override
//	public boolean update(Group updateGroup) {
//			if (!isItemsPresent(updateGroup) && jpaGroupsDao.update(updateGroup)) {
//				log.info(updateGroup.getName() + " has been successfully updated.");
//				return true;
//			}
//			log.info(updateGroup + " update - error. Maybe the group name is already in the database.");
//		return false;
//	}
//	
//	private Collection<GroupsView> convertorFromView(Collection<Group> collection) {
//		return collection.stream()
//				.sorted()
//				.map(this::toView)
//				.collect(toList());
//	}
//
//	@Override
//	public GroupsView toView(Group model) {
//		return GroupsView.builder().id(model.getId()).nameOfGroup(model.getName()).build();
//	}
}
