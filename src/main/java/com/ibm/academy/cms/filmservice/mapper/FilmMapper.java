package com.ibm.academy.cms.filmservice.mapper;

import com.ibm.academy.cms.filmservice.dto.FilmDto;
import com.ibm.academy.cms.filmservice.entity.Film;
import org.mapstruct.Mapper;

@Mapper(uses = {DataMapper.class})
public interface FilmMapper {
    FilmDto toDto(Film film);

    Film toEntity(FilmDto filmDto);
}
