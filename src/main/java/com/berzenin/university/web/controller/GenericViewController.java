package com.berzenin.university.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface GenericViewController<E> {

	String findAll(Model model);	
	String findById(Long id);
	String deleteEntity(@PathVariable("id") Long id, Model model);
	String add(E entity);
	String update(E entity);
	
	

}