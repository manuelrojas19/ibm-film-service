package com.ibm.academy.cms.filmservice.assembler;

import com.google.common.collect.Streams;
import com.ibm.academy.cms.filmservice.controller.DirectorController;
import com.ibm.academy.cms.filmservice.dto.DirectorDto;
import com.ibm.academy.cms.filmservice.entity.Director;
import com.ibm.academy.cms.filmservice.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class DirectorAssembler implements RepresentationModelAssembler<Director, DirectorDto> {

    private final PersonMapper personMapper;

    @Override
    public DirectorDto toModel(Director entity) {
        DirectorDto dto = personMapper.toDto(entity);
        dto.add(linkTo(methodOn(DirectorController.class).findById(entity.getId())).withSelfRel().expand());
        dto.add(linkTo(methodOn(DirectorController.class).update(entity.getId(), null)).withRel("update").expand());
        dto.add(linkTo(methodOn(DirectorController.class).delete(entity.getId())).withRel("delete").expand());
        return dto;
    }

    @Override
    public CollectionModel<DirectorDto> toCollectionModel(Iterable<? extends Director> entities) {
        CollectionModel<DirectorDto> dtos = CollectionModel.of(Streams.stream(entities)
                .map(this::toModel).collect(Collectors.toList()));
        dtos.add(linkTo(methodOn(DirectorController.class).findAll()).withSelfRel().expand());
        dtos.add(linkTo(methodOn(DirectorController.class).create(null)).withRel("create").expand());
        return dtos;
    }
}
