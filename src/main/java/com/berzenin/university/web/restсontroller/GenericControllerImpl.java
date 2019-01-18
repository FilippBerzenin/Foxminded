package com.berzenin.university.web.restсontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.berzenin.university.service.controller.GenericService;

public abstract class GenericControllerImpl<E, S extends GenericService<E>> implements GenericController<E> {
	
	protected final S service;

	@Autowired
	public GenericControllerImpl(S service) {
		this.service=service;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<E> getAll() {
		return service.findAll();
	}


//	@Override
//	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public List<E> getAll() {
//		return (List<E>) service.findAll();
//	}

//
//	@Override
//	public ResponseEntity<E> save(E entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}