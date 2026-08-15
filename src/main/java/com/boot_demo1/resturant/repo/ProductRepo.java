package com.boot_demo1.resturant.repo;

import com.boot_demo1.resturant.dto.ProductDTO;
import com.boot_demo1.resturant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> getProductByCategoryId(Long categoryId);

    boolean existsByProductNameIgnoreCase(String name);

    List<Product> getProductByCategory_CategoryName(String categoryName);
}
