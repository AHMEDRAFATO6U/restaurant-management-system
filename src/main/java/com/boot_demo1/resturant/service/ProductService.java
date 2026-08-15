package com.boot_demo1.resturant.service;

import com.boot_demo1.resturant.dto.ProductDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getproductByCategoryId(Long id);

    ProductDTO createProduct(@Valid ProductDTO productDTO);

    ProductDTO updateProduct(@Valid ProductDTO productDTO);

    ProductDTO getByProductId(Long id);

    List<ProductDTO> getAllProductId();

    void deleteByProductId(Long productId);

    List<ProductDTO> saveProducts(List<ProductDTO> productDTOs);

    List<ProductDTO> updateProducts(List<ProductDTO> productDTOs);

    List<ProductDTO> getProductByCategoryName(String categoryName);
}
