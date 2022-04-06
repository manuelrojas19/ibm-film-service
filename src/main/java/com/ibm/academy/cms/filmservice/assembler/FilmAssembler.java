package com.ibm.academy.cms.filmservice.assembler;

import com.google.common.collect.Streams;
import com.ibm.academy.cms.filmservice.controller.FilmController;
import com.ibm.academy.cms.filmservice.dto.FilmDto;
import com.ibm.academy.cms.filmservice.entity.Film;
import com.ibm.academy.cms.filmservice.mapper.FilmMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class FilmAssembler implements RepresentationModelAssembler<Film, FilmDto> {

    private final FilmMapper mapper;

    @Override
    public FilmDto toModel(Film entity) {
        FilmDto dto = mapper.toDto(entity);
        dto.add(linkTo(methodOn(FilmController.class).findById(entity.getId())).withSelfRel().expand());
        dto.add(linkTo(methodOn(FilmController.class).update(entity.getId(), null)).withRel("update").expand());
        dto.add(linkTo(methodOn(FilmController.class).delete(entity.getId())).withRel("delete").expand());
        return dto;
    }

    @Override
    public CollectionModel<FilmDto> toCollectionModel(Iterable<? extends Film> entities) {
        CollectionModel<FilmDto> dtos = CollectionModel.of(Streams.stream(entities)
                .map(this::toModel).collect(Collectors.toList()));
        dtos.add(linkTo(methodOn(FilmController.class).findAll(null)).withSelfRel().expand());
        dtos.add(linkTo(methodOn(FilmController.class).create(null)).withRel("create").expand());
        return dtos;
    }
}
