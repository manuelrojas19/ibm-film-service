package com.ibm.academy.cms.filmservice.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<E> {
    E findById(Long id);

    List<E> findAll();

    Page<E> findAll(Predicate predicate, Pageable pageable);

    E create(E entity);

    E update(Long id, E entity);

    void delete(Long id);
}
