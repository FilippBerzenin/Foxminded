package com.berzenin.university.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl<E, R extends CrudRepository<E, Long>> implements GenericService<E> {
	
    protected final R repository;

    @Autowired
    public GenericServiceImpl(R repository) {
        this.repository = repository;
    }

//	protected JpaRepository<E, Long> repository;
//
//	public GenericServiceImpl(JpaRepository<E, Long> repository) {
//		this.repository = repository;
//	}

	@Override
	public void saveOrUpdate(E entity) {
		repository.save(entity);
	}

	@Override
	public List<E> findAll() {
		return (List<E>) repository.findAll();
	}

	@Override
	public Optional<E> get(Long id) {
		return repository.findById(id);
	}

	@Override
	public void add(E entity) {
		repository.save(entity);
	}

	@Override
	public void update(E entity) {
		repository.save(entity);
	}

	@Override
	public void remove(E entity) {
		repository.delete(entity);
	}

	@Override
	public void removeById(Long id) {
		repository.deleteById(id);
		;
	}
}