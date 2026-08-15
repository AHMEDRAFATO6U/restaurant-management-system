package com.boot_demo1.resturant.service;

import com.boot_demo1.resturant.dto.CategoryDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryService {
     List<CategoryDto> getAllCategories();

    CategoryDto saveCategory(@Valid CategoryDto categoryDto);

    CategoryDto updateCategory(@Valid CategoryDto categoryDto);

    List<CategoryDto> saveListOfCategories(@Valid List<CategoryDto> categoryDtos);

    List<CategoryDto> updateListOfCategories(@Valid List<CategoryDto> categoryDtos);

    CategoryDto getCategoryById(@Valid Long id);

    void deleteCategoryById(@Valid Long id);
}
