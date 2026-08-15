package com.boot_demo1.resturant.mappers;

import com.boot_demo1.resturant.dto.CategoryDto;
import com.boot_demo1.resturant.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);

}