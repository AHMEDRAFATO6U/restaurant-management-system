package com.boot_demo1.resturant.service.imple;

import com.boot_demo1.resturant.dto.CategoryDto;
import com.boot_demo1.resturant.exception.DuplicateResourceException;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.mappers.CategoryMapper;
import com.boot_demo1.resturant.model.Category;
import com.boot_demo1.resturant.repo.CategoryRepo;
import com.boot_demo1.resturant.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImple implements CategoryService {

    private final CategoryRepo categoryRepo;
    private  final CategoryMapper categoryMapper ;

    @Override
    public  List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());


    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        if(categoryRepo.existsByCategoryNameIgnoreCase(categoryDto.getCategoryName())){
            throw new DuplicateResourceException("Category already exists");
        }
        Category category = categoryMapper.toCategory(categoryDto);
        category.setId(null);
        Category savedCategory = categoryRepo.save(category);
        return categoryMapper.toCategoryDto(savedCategory);
    }



    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepo.findById(categoryDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryFlag(categoryDto.getCategoryFlag());
        category.setCategoryLogo(categoryDto.getCategoryLogo());
        Category updatedCategory = categoryRepo.save(category);
        return categoryMapper.toCategoryDto(updatedCategory);

    }

    @Override
    public List<CategoryDto> saveListOfCategories(List<CategoryDto> categoryDtos) {

        List<Category> categories = categoryDtos.stream()
                .map(categoryDto -> {
                    Category category = categoryMapper.toCategory(categoryDto);
                    category.setId(null);
                    return category;
                })
                .collect(Collectors.toList());
        List<Category> savedCategories = categoryRepo.saveAll(categories);

        return savedCategories.stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());


    }

    @Override
    public List<CategoryDto> updateListOfCategories(List<CategoryDto> categoryDtos) {
        Set<Long> categoryIds = categoryDtos.stream()
                .map(CategoryDto::getId)
                .collect(Collectors.toSet());

        List<Category> existingCategories = categoryRepo.findAllById(categoryIds);

        Map<Long ,Category> categoryMap = existingCategories.stream()
                .collect(Collectors.toMap(Category::getId, category -> category));

        List<Category> updatedCategories = categoryDtos.stream()
                .map(categoryDto -> {
                    Category category = categoryMap.get(categoryDto.getId());
                    if(category == null){
                        throw new ResourceNotFoundException("Category not found");
                    }
                    category.setCategoryName(categoryDto.getCategoryName());
                    category.setCategoryFlag(categoryDto.getCategoryFlag());
                    category.setCategoryLogo(categoryDto.getCategoryLogo());
                    return category;

                }).collect(Collectors.toList());

        List<Category> savedCategories = categoryRepo.saveAll(updatedCategories);

        return savedCategories.stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());

    }

    @Override
    public CategoryDto getCategoryById(Long id) {

        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public void deleteCategoryById(Long id) {

        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryRepo.delete(category);

    }


}
