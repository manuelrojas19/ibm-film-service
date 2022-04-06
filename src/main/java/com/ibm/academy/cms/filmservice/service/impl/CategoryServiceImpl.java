package com.ibm.academy.cms.filmservice.service.impl;

import com.ibm.academy.cms.filmservice.entity.Category;
import com.ibm.academy.cms.filmservice.repository.CategoryRepository;
import com.ibm.academy.cms.filmservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryRepository> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public Category update(Long id, Category category) {
        Category categoryToUpdate = this.findById(id);
        categoryToUpdate.setName(category.getName());
        return repository.save(categoryToUpdate);
    }
}
