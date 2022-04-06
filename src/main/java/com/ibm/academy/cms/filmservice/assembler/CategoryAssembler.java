package com.ibm.academy.cms.filmservice.assembler;

import com.google.common.collect.Streams;
import com.ibm.academy.cms.filmservice.controller.CategoryController;
import com.ibm.academy.cms.filmservice.dto.CategoryDto;
import com.ibm.academy.cms.filmservice.entity.Category;
import com.ibm.academy.cms.filmservice.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class CategoryAssembler implements RepresentationModelAssembler<Category, CategoryDto> {
    
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto toModel(Category entity) {
        CategoryDto dto = categoryMapper.toDto(entity);
        dto.add(linkTo(methodOn(CategoryController.class).findById(entity.getId())).withSelfRel().expand());
        dto.add(linkTo(methodOn(CategoryController.class).update(entity.getId(), null)).withRel("update").expand());
        dto.add(linkTo(methodOn(CategoryController.class).delete(entity.getId())).withRel("delete").expand());
        return dto;
    }

    @Override
    public CollectionModel<CategoryDto> toCollectionModel(Iterable<? extends Category> entities) {
        CollectionModel<CategoryDto> dtos = CollectionModel.of(Streams.stream(entities)
                .map(this::toModel).collect(Collectors.toList()));
        dtos.add(linkTo(methodOn(CategoryController.class).findAll()).withSelfRel().expand());
        dtos.add(linkTo(methodOn(CategoryController.class).create(null)).withRel("create").expand());
        return dtos;
    }
}
