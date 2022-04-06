package com.ibm.academy.cms.filmservice.controller;

import com.ibm.academy.cms.filmservice.assembler.CategoryAssembler;
import com.ibm.academy.cms.filmservice.dto.CategoryDto;

import com.ibm.academy.cms.filmservice.entity.Category;
import com.ibm.academy.cms.filmservice.mapper.CategoryMapper;
import com.ibm.academy.cms.filmservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryAssembler categoryAssembler;
    private final CategoryMapper categoryMapper;

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<CollectionModel<CategoryDto>> findAll() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categoryAssembler.toCollectionModel(categories), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(categoryAssembler.toModel(category), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<CategoryDto> create(@Validated @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.create(categoryMapper.toEntity(categoryDto));
        return new ResponseEntity<>(categoryAssembler.toModel(category), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @Validated @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.update(id, categoryMapper.toEntity(categoryDto));
        return new ResponseEntity<>(categoryAssembler.toModel(category), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
