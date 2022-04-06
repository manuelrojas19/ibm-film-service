package com.ibm.academy.cms.filmservice.service.impl;

import com.ibm.academy.cms.filmservice.entity.Actor;
import com.ibm.academy.cms.filmservice.repository.ActorRepository;
import com.ibm.academy.cms.filmservice.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl extends GenericServiceImpl<Actor, ActorRepository> implements ActorService {

    @Autowired
    public ActorServiceImpl(ActorRepository repository) {
        super(repository);
    }

    @Override
    public Actor update(Long id, Actor actor) {
        Actor actorToUpdate = this.findById(id);
        actorToUpdate.setName(actor.getName());
        actorToUpdate.setDescription(actor.getDescription());
        actorToUpdate.setBornDate(actor.getBornDate());
        actorToUpdate.setBornPlace(actor.getBornPlace());
        return repository.save(actorToUpdate);
    }
}
