package com.berzenin.university.web.restсontroller;

import java.util.List;

public interface GenericController <E> {

	public List<E> getAll();

//    @PostMapping
//    ResponseEntity<E> save(@RequestBody E entity);
	
}