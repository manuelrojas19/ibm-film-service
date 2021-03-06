package com.ibm.academy.cms.filmservice.controller;

import com.ibm.academy.cms.filmservice.assembler.ActorAssembler;
import com.ibm.academy.cms.filmservice.dto.ActorDto;
import com.ibm.academy.cms.filmservice.entity.Actor;
import com.ibm.academy.cms.filmservice.mapper.PersonMapper;
import com.ibm.academy.cms.filmservice.service.ActorService;
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
@RequestMapping("/api/v1/actors")
@AllArgsConstructor
public class ActorController {

    private final ActorService actorService;
    private final ActorAssembler actorAssembler;
    private final PersonMapper personMapper;
    private final PagedResourcesAssembler<Actor> pagedResourcesAssembler;

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<CollectionModel<ActorDto>> findAll(@QuerydslPredicate(root = Actor.class) Predicate predicate,
                                                             Pageable pageable) {
        Page<Actor> actorsPage = actorService.findAll(predicate, pageable);
        return new ResponseEntity<>(pagedResourcesAssembler.toModel(actorsPage, actorAssembler), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> findById(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        return new ResponseEntity<>(actorAssembler.toModel(actor), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<ActorDto> create(@Validated @RequestBody ActorDto actorDto) {
        Actor actor = actorService.create(personMapper.toEntity(actorDto));
        return new ResponseEntity<>(actorAssembler.toModel(actor), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ActorDto> update(@PathVariable Long id, @Validated @RequestBody ActorDto actorDto) {
        Actor actor = actorService.update(id, personMapper.toEntity(actorDto));
        return new ResponseEntity<>(actorAssembler.toModel(actor), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
