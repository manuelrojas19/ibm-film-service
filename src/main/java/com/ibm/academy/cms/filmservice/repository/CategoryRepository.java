package com.ibm.academy.cms.filmservice.repository;

import com.ibm.academy.cms.filmservice.entity.Category;
import com.ibm.academy.cms.filmservice.entity.Film;
import com.ibm.academy.cms.filmservice.entity.QCategory;
import com.ibm.academy.cms.filmservice.entity.QFilm;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>,
        QuerydslPredicateExecutor<Category>,
        QuerydslBinderCustomizer<QCategory> {

    @Query
    Optional<Category> findByName(String name);

    @Override
    default void customize(QuerydslBindings bindings, QCategory root) {
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value)
        );
        bindings.excluding(root.version);
    }
}
