package com.berzenin.university.service.controller;

import java.util.List;

public interface GenericService<E> {
    public E saveOrUpdate(E entity);
    public List<E> findAll();
    public E findById(Long id);
    public E add(E entity);
    public E update(E entity);
    public void remove(E entity);
    public void removeById(Long id);
}

