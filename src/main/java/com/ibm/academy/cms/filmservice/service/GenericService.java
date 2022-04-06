package com.ibm.academy.cms.filmservice.service;

import java.util.List;

public interface GenericService<E> {
    E findById(Long id);

    List<E> findAll();

    E create(E entity);

    E update(Long id, E entity);

    void delete(Long id);
}
