package com.berzenin.university.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.service.controller.GenericService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public abstract class GenericViewControllerImpl<E, S extends GenericService<E>> implements GenericViewController<E> {
	
	@Autowired
	protected S service;
	
	protected String message = "Something wrong";
	protected List<E> entites;
	protected String page = "error";
	
	@Override
	public String findAll(Model model) {
		message = "All entity";
		entites = service.findAll();
		setModelAttribute(model);
		return page;
	}	
	
	@Override
	public String findById(Long id, Model model) {
		try {
			service.findById(id);
			setModelAttribute(model);
			return page;	
		} catch (RuntimeException e) {
			this.setModelAttributeWhenthrowException(e, model);
			return page;
		}
	}
	
	@Override
	public String add(
			@ModelAttribute("entity") @Valid E entity,
			BindingResult result, 
			Model model) {
		 {if (result.hasErrors()) {
				message = "Something wrong with parameters";
				setModelAttribute(model);
				return page;
			}
			try {
				service.saveOrUpdate(entity);
				message = "Entity was successful save";
				setModelAttribute(model);
				return page;
			} catch (RuntimeException e) {
				this.setModelAttributeWhenthrowException(e, model);
				return page;
			}
		 }
	}

	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String deleteEntity(@PathVariable("id") Long id, Model model) {
		try {
			service.removeById(id);
			entites = service.findAll();
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
	
	public String update(@Valid E entity, BindingResult result, Model model) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void setModelAttribute(Model model) {
		model.addAttribute("page", page);
		model.addAttribute("message", message);
		model.addAttribute("listOfEntites", entites);
	}
	
	protected void setModelAttributeWhenthrowException (RuntimeException e, Model model) {
		log.error("Error "+e);
		message = "Error "+e;
		entites = service.findAll();
		setModelAttribute(model);
	}
}