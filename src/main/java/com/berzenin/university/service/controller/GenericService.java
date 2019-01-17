package com.berzenin.university.service.controller;

import java.util.List;
import java.util.Optional;

public interface GenericService<E> {
    public void saveOrUpdate(E entity);
    public List<E> findAll();
    public Optional<E> get(Long id);
    public void add(E entity);
    public void update(E entity);
    public void remove(E entity);
    public void removeById(Long id);
}

