package com.berzenin.university.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.model.university.Group;
import com.berzenin.university.service.controller.GroupService;
import com.berzenin.university.web.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value="/groups")
public class GroupViewController {

	private final GroupService groupService;
	
	private String message = "Something wrong";
	private List<Group> groups;

	@RequestMapping(value="/show/all", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getGroupsList(Model model) {
		message = "All groups";
		groups = groupService.findAll();
		setModelAttribute(model);
		return "groups";
	}

	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String searchGroupByName(@RequestParam String filter, Model model) {
 		if (filter != null && !filter.isEmpty()) {
 			try {
 				groups = groupService.searchGroupsByName(filter);
 				message = "The group was found";
 				setModelAttribute(model);
 				} catch (NotFoundException e) {
 				message = "The group wasn't found";
 				return "groups";
 			} finally {
 				setModelAttribute(model);	
 			} 			
 		} else {
 			setModelAttribute(model);
 	 		message = "Wrong parametr for search";
 	 	}
 		return "groups";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addNewGroup(@RequestParam String newGroupsName, Model model) {
		try {
			if (newGroupsName != null && newGroupsName.length()>0 && groupService.addNewGroup(newGroupsName)) {
				message = "New group sucessful created";
				groups = groupService.findAll();
				}
			return "groups";
		} catch (RuntimeException e) {
			log.info("group was not created" + e);
			message = newGroupsName+ " group was not created.";
			return "groups";
		} finally {
			setModelAttribute(model);
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String deleteGroup(@PathVariable("id") Long id, Model model) {
		try {
			groupService.delete(id);
			groups = groupService.findAll();
			message = id+ " Successfully deleted.";
			return "groups";
		} catch (RuntimeException e) {
			log.info("Delete failed" + e);
			message = id+ " delete failed.";
			return "groups";
		} finally {
			setModelAttribute(model);
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String updateGroup(@PathVariable("id") Long id, @RequestParam(value="newGroupName") String newGroupName, Model model) {
		try {
			Group group = groupService.findById(id);
			group.setName(newGroupName);
			groupService.save(group);
			groups = groupService.findAll();
			message = "group was sucessful updated";
			return "groups";
		} catch (RuntimeException e) {
			log.info("group was not updated " + e);
			message = id+ " group was not updated.";
			return "groups";
		} finally {
			setModelAttribute(model);
		}
	}
	
	private void setModelAttribute(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("groupsList", groups);
	}
}
