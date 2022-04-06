package com.ibm.academy.cms.filmservice.service;

import com.ibm.academy.cms.filmservice.entity.Film;

import java.util.List;

public interface FilmService extends GenericService<Film> {
    Film addActorToFilm(Long actorId, Long filmId, List<String> roles);
    Film addDirectorToFilm(Long directorId, Long filmId);
    Film addCategoryToFilm(Long categoryId, Long filmId);
    List<Film> findAllByCategory(String categoryName);
}
