package com.ibm.academy.cms.filmservice.repository;

import com.ibm.academy.cms.filmservice.entity.Actor;
import com.ibm.academy.cms.filmservice.entity.ActorRole;
import com.ibm.academy.cms.filmservice.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRoleRepository extends JpaRepository<ActorRole, Long> {
    List<ActorRole> findAllByActorAndFilm(Actor actor, Film film);
}
