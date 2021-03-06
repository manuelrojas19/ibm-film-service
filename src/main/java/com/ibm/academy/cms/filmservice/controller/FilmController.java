package com.ibm.academy.cms.filmservice.controller;

import com.ibm.academy.cms.filmservice.assembler.FilmAssembler;
import com.ibm.academy.cms.filmservice.dto.AddActorToFilmRequestDto;
import com.ibm.academy.cms.filmservice.dto.AddCategoryToFilmRequestDto;
import com.ibm.academy.cms.filmservice.dto.AddDirectorToFilmRequestDto;
import com.ibm.academy.cms.filmservice.dto.FilmDto;
import com.ibm.academy.cms.filmservice.entity.Film;
import com.ibm.academy.cms.filmservice.mapper.FilmMapper;
import com.ibm.academy.cms.filmservice.service.FilmService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/films")
@AllArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final FilmAssembler filmAssembler;
    private final FilmMapper filmMapper;
    private final PagedResourcesAssembler<Film> pagedResourcesAssembler;

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<CollectionModel<FilmDto>> findAll(@QuerydslPredicate(root = Film.class) Predicate predicate,
                                                            Pageable pageable) {
        Page<Film> filmsPage = filmService.findAll(predicate, pageable);
        return new ResponseEntity<>(pagedResourcesAssembler.toModel(filmsPage, filmAssembler), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> findById(@PathVariable Long id) {
        Film film = filmService.findById(id);
        return new ResponseEntity<>(filmAssembler.toModel(film), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<FilmDto> create(@Validated @RequestBody FilmDto filmDto) {
        Film film = filmService.create(filmMapper.toEntity(filmDto));
        return new ResponseEntity<>(filmAssembler.toModel(film), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FilmDto> update(@PathVariable Long id, @Validated @RequestBody FilmDto filmDto) {
        Film film = filmService.update(id, filmMapper.toEntity(filmDto));
        return new ResponseEntity<>(filmAssembler.toModel(film), HttpStatus.ACCEPTED);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("permitAll()")
    @PatchMapping("/{filmId}/add-actor")
    public ResponseEntity<FilmDto> addActor(@PathVariable Long filmId,
                                            @Validated @RequestBody AddActorToFilmRequestDto request) {
        Film film = filmService.addActorToFilm(request.getActorId(), filmId, request.getRoles());
        return new ResponseEntity<>(filmAssembler.toModel(film), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{filmId}/add-director")
    public ResponseEntity<FilmDto> addDirector(@PathVariable Long filmId,
                                               @Validated @RequestBody AddDirectorToFilmRequestDto request) {
        Film film = filmService.addDirectorToFilm(request.getDirectorId(), filmId);
        return new ResponseEntity<>(filmAssembler.toModel(film), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{filmId}/add-category")
    public ResponseEntity<FilmDto> addCategory(@PathVariable Long filmId,
                                               @Validated @RequestBody AddCategoryToFilmRequestDto request) {
        Film film = filmService.addDirectorToFilm(request.getCategoryId(), filmId);
        return new ResponseEntity<>(filmAssembler.toModel(film), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filmService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
