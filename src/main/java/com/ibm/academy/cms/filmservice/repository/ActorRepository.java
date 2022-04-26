package com.ibm.academy.cms.filmservice.repository;

import com.ibm.academy.cms.filmservice.entity.Actor;
import com.ibm.academy.cms.filmservice.entity.Film;
import com.ibm.academy.cms.filmservice.entity.QActor;
import com.ibm.academy.cms.filmservice.entity.QFilm;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>,
        QuerydslPredicateExecutor<Actor>,
        QuerydslBinderCustomizer<QActor> {

    @Override
    default void customize(QuerydslBindings bindings, QActor root) {
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value)
        );
        bindings.excluding(root.version);
    }
}
