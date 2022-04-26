package com.ibm.academy.cms.filmservice.service.impl;

import com.ibm.academy.cms.filmservice.exception.NotFoundException;
import com.ibm.academy.cms.filmservice.service.GenericService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
public abstract class GenericServiceImpl<E,
        R extends JpaRepository<E, Long>
                & QuerydslPredicateExecutor<E>
                & QuerydslBinderCustomizer<? extends EntityPathBase<E>>>
        implements GenericService<E> {

    public static final String NOT_FOUND_ERROR_MSG = "Resource was not found";

    protected final R repository;

    @Override
    @Transactional(readOnly = true)
    public E findById(Long id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_ERROR_MSG));
        log.info("Retrieved info --> {}", entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() {
        List<E> entities = repository.findAll();
        if (entities.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        log.info("Retrieved info ---> {}", entities);
        return entities;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<E> findAll(Predicate predicate, Pageable pageable) {
        Page<E> entitiesPage = repository.findAll(predicate, pageable);
        if (entitiesPage.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        log.info("Retrieved info ---> {}", entitiesPage.getContent());
        return entitiesPage;
    }

    @Override
    @Transactional
    public E create(E entity) {
        log.info("Persisting in db ---> {}", entity);
        E savedEntity = repository.save(entity);
        log.info("Retrieved info ---> {}", savedEntity);
        return savedEntity;
    }

    @Override
    @Transactional
    public abstract E update(Long id, E entity);

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting in db entity with id ---> {}", id);
        repository.deleteById(id);
    }
}
