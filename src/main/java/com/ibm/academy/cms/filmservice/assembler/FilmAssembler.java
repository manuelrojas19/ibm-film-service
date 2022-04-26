package com.ibm.academy.cms.filmservice.assembler;

import com.google.common.collect.Streams;
import com.ibm.academy.cms.filmservice.controller.FilmController;
import com.ibm.academy.cms.filmservice.dto.AddActorToFilmRequestDto;
import com.ibm.academy.cms.filmservice.dto.AddCategoryToFilmRequestDto;
import com.ibm.academy.cms.filmservice.dto.AddDirectorToFilmRequestDto;
import com.ibm.academy.cms.filmservice.dto.FilmDto;
import com.ibm.academy.cms.filmservice.entity.Film;
import com.ibm.academy.cms.filmservice.mapper.FilmMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class FilmAssembler implements RepresentationModelAssembler<Film, FilmDto> {

    private final FilmMapper mapper;

    @Override
    public @Nonnull FilmDto toModel(@Nonnull Film entity) {
        FilmDto dto = mapper.toDto(entity);
        dto.add(linkTo(methodOn(FilmController.class).findById(entity.getId())).withSelfRel().expand());
        dto.add(linkTo(methodOn(FilmController.class).update(entity.getId(), null)).withRel("update").expand());
        dto.add(linkTo(methodOn(FilmController.class).delete(entity.getId())).withRel("delete").expand());
        dto.add(linkTo(methodOn(FilmController.class).addActor(entity.getId(), new AddActorToFilmRequestDto())).withRel("addActor").expand());
        dto.add(linkTo(methodOn(FilmController.class).addCategory(entity.getId(), new AddCategoryToFilmRequestDto())).withRel("addCategory").expand());
        dto.add(linkTo(methodOn(FilmController.class).addDirector(entity.getId(), new AddDirectorToFilmRequestDto())).withRel("addDirector").expand());
        return dto;
    }

    @Override
    public @Nonnull CollectionModel<FilmDto> toCollectionModel(@Nonnull Iterable<? extends Film> entities) {
        CollectionModel<FilmDto> dtos = CollectionModel.of(Streams.stream(entities)
                .map(this::toModel).collect(Collectors.toList()));
        dtos.add(linkTo(methodOn(FilmController.class).findAll(null, null)).withSelfRel().expand());
        dtos.add(linkTo(methodOn(FilmController.class).create(null)).withRel("create").expand());
        return dtos;
    }
}
