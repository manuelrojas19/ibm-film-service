package com.ibm.academy.cms.filmservice.mapper;

import com.ibm.academy.cms.filmservice.dto.ActorDto;
import com.ibm.academy.cms.filmservice.dto.DirectorDto;
import com.ibm.academy.cms.filmservice.dto.PersonDto;
import com.ibm.academy.cms.filmservice.entity.Actor;
import com.ibm.academy.cms.filmservice.entity.Director;
import com.ibm.academy.cms.filmservice.entity.Person;
import org.mapstruct.Mapper;

@Mapper(uses = {DataMapper.class})
public interface PersonMapper {

    default PersonDto toDto(Person person) {
        if (person instanceof Actor)
            return toDto((Actor) person);
        if (person instanceof Director)
            return toDto((Director) person);
        else
            return null;
    }

    default Person toEntity(PersonDto personDto) {
        if (personDto instanceof ActorDto)
            return toEntity((ActorDto) personDto);
        if (personDto instanceof DirectorDto)
            return toEntity((DirectorDto) personDto);
        else
            return null;
    }

    ActorDto toDto(Actor actor);
    Actor toEntity(ActorDto actorDto);

    DirectorDto toDto(Director director);
    Director toEntity(DirectorDto directorDto);
}
