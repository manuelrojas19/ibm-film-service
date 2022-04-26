package com.ibm.academy.cms.filmservice.mapper;

import com.ibm.academy.cms.filmservice.dto.FilmDto;
import com.ibm.academy.cms.filmservice.entity.Film;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.stream.Collectors;

@Mapper(uses = {DataMapper.class})
public interface FilmMapper {
    FilmDto toDto(Film film);

    Film toEntity(FilmDto filmDto);

    @AfterMapping
    default void deleteNestedActorData(@MappingTarget FilmDto filmDto) {
        filmDto.setCasts(
                filmDto.getCasts().stream()
                        .peek(castDto -> {
                            castDto.getActor().setFilms(null);
                            castDto.getActor().setCreatedAt(null);
                            castDto.getActor().setCreatedBy(null);
                            castDto.getActor().setLastModifiedAt(null);
                            castDto.getActor().setLastModifiedBy(null);
                            castDto.getActor().setBornDate(null);
                            castDto.getActor().setBornPlace(null);
                            castDto.getActor().setCreatedBy(null);
                            castDto.getActor().setVersion(null);
                        })
                        .collect(Collectors.toSet())
        );
    }
}
