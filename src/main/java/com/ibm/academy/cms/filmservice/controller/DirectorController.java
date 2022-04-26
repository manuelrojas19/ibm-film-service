package com.ibm.academy.cms.filmservice.controller;

import com.ibm.academy.cms.filmservice.assembler.DirectorAssembler;
import com.ibm.academy.cms.filmservice.dto.DirectorDto;
import com.ibm.academy.cms.filmservice.entity.Director;
import com.ibm.academy.cms.filmservice.entity.Film;
import com.ibm.academy.cms.filmservice.mapper.PersonMapper;
import com.ibm.academy.cms.filmservice.service.DirectorService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/directors")
@AllArgsConstructor
public class DirectorController {

    private final DirectorService directorService;
    private final DirectorAssembler directorAssembler;
    private final PersonMapper personMapper;
    private final PagedResourcesAssembler<Director> pagedResourcesAssembler;

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<CollectionModel<DirectorDto>> findAll(@QuerydslPredicate(root = Director.class) Predicate predicate,
                                                                Pageable pageable) {
        Page<Director> directorsPage = directorService.findAll(predicate, pageable);
        PagedModel<DirectorDto> response = pagedResourcesAssembler.toModel(directorsPage, directorAssembler);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> findById(@PathVariable Long id) {
        Director director = directorService.findById(id);
        return new ResponseEntity<>(directorAssembler.toModel(director), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DirectorDto> create(@Validated @RequestBody DirectorDto directorDto) {
        Director director = directorService.create(personMapper.toEntity(directorDto));
        return new ResponseEntity<>(directorAssembler.toModel(director), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DirectorDto> update(@PathVariable Long id, @Validated @RequestBody DirectorDto directorDto) {
        Director director = directorService.update(id, personMapper.toEntity(directorDto));
        return new ResponseEntity<>(directorAssembler.toModel(director), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
