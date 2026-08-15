package com.boot_demo1.resturant.mappers;

import com.boot_demo1.resturant.dto.ProductDTO;
import com.boot_demo1.resturant.exception.ResourceNotFoundException;
import com.boot_demo1.resturant.model.Category;
import com.boot_demo1.resturant.model.Product;
import com.boot_demo1.resturant.repo.CategoryRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected CategoryRepo categoryRepo;

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "description", source = "productDescription")
    public abstract ProductDTO productToProductDTO(Product product);

    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "productDescription", source = "description")
    public abstract Product productDTOToProduct(ProductDTO productDTO);

    public abstract List<ProductDTO> productListToProductDTOList(List<Product> productList);
    public abstract List<Product> productDTOListToProductList(List<ProductDTO> productDTOList);

    protected Category mapCategoryIdToCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CATEGORY ID: " + categoryId + " NOT FOUND"));
    }
}