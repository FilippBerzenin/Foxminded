package com.berzenin.university.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.service.controller.GenericService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GenericViewControllerImpl<E, S extends GenericService<E>> implements GenericViewController<E> {
	
	@Autowired
	protected S service;
	
	protected String message = "Something wrong";
	protected List<E> enites;
	protected String page = "error";

	@Override
	@RequestMapping(value="/show/all", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getAll(Model model) {
		message = "All groups";
		enites = service.findAll();
		setModelAttribute(model);
		return page;
	}	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String deleteEntity(@PathVariable("id") Long id, Model model) {
		try {
			service.removeById(id);
			enites = service.findAll();
			message = id+ " Successfully deleted.";
			return page;
		} catch (RuntimeException e) {
			log.info("Delete failed" + e);
			message = id+ " delete failed.";
			return page;
		} finally {
			setModelAttribute(model);
		}
	}
	
	protected void setModelAttribute(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("groupsList", enites);
	}


}