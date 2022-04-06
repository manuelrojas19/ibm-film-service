package com.ibm.academy.cms.filmservice.service.impl;

import com.ibm.academy.cms.filmservice.exception.NotFoundException;
import com.ibm.academy.cms.filmservice.service.GenericService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public abstract class GenericServiceImpl<E, R extends JpaRepository<E, Long>> implements GenericService<E> {

    public static final String NOT_FOUND_ERROR_MSG = "Resource was not found";

    protected final R repository;

    @Override
    @Transactional(readOnly = true)
    public E findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR_MSG));
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public E create(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public abstract E update(Long id, E entity);

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
