package com.ibm.academy.cms.filmservice.service.impl;

import com.ibm.academy.cms.filmservice.entity.*;
import com.ibm.academy.cms.filmservice.exception.BadRequestException;
import com.ibm.academy.cms.filmservice.exception.NotFoundException;
import com.ibm.academy.cms.filmservice.repository.*;
import com.ibm.academy.cms.filmservice.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FilmServiceImpl extends GenericServiceImpl<Film, FilmRepository> implements FilmService {

    public static final String ACTOR_NOT_FOUND_ERROR_MSG = "Actor was not found";
    public static final String ACTOR_CURRENTLY_ADDED_ERROR_MSG = "This actor is currently added to this film";
    public static final String CATEGORY_CURRENTLY_ADDED_ERROR_MSG = "This category is currently added to this film";
    public static final String DIRECTOR_CURRENTLY_ADDED_ERROR_MSG = "This director is currently added to this film";
    public static final String DIRECTOR_NOT_FOUND_ERROR_MSG = "Director was not found";
    public static final String CATEGORY_NOT_FOUND_ERROR_MSG = "Category was not found";

    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final CategoryRepository categoryRepository;
    private final ActorRoleRepository actorRoleRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository repository,
                           ActorRepository actorRepository,
                           DirectorRepository directorRepository,
                           CategoryRepository categoryRepository,
                           ActorRoleRepository actorRoleRepository) {
        super(repository);
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
        this.categoryRepository = categoryRepository;
        this.actorRoleRepository = actorRoleRepository;
    }

    @Override
    @Transactional
    public Film update(Long id, Film film) {
        Film filmToUpdate = this.findById(id);
        filmToUpdate.setTitle(film.getTitle());
        filmToUpdate.setDescription(film.getDescription());
        filmToUpdate.setDate(film.getDate());
        return repository.save(filmToUpdate);
    }

    @Override
    @Transactional
    public Film addActorToFilm(Long actorId, Long filmId, List<String> actorRoles) {

        Film film = this.findById(filmId);
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new NotFoundException(ACTOR_NOT_FOUND_ERROR_MSG));

        log.info("actorRoles --> {}", film.getActorsAndRoles());

        List<ActorRole> actorRolesList = actorRoleRepository.findAllByActorAndFilm(actor, film);

        actorRoleRepository.save(ActorRole.builder()
                .film(film)
                .actor(actor)
                .role("Nanahue")
                .build());

        return film;
    }

    @Override
    @Transactional
    public Film addDirectorToFilm(Long directorId, Long filmId) {
        Film film = this.findById(filmId);
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new NotFoundException(DIRECTOR_NOT_FOUND_ERROR_MSG));
        if (film.getDirectors().contains(director))
            throw new BadRequestException(DIRECTOR_CURRENTLY_ADDED_ERROR_MSG);
        film.getDirectors().add(director);
        return repository.save(film);
    }

    @Override
    @Transactional
    public Film addCategoryToFilm(Long categoryId, Long filmId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_ERROR_MSG));
        Film film = this.findById(filmId);

        if (film.getCategories().contains(category))
            throw new BadRequestException(CATEGORY_CURRENTLY_ADDED_ERROR_MSG);
        film.getCategories().add(category);

        return repository.save(film);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> findAllByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND_ERROR_MSG));
        return repository.findAll().stream()
                .filter(f -> f.getCategories().contains(category))
                .collect(Collectors.toList());
    }
}
