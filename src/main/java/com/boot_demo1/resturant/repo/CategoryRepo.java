package com.boot_demo1.resturant.repo;

import com.boot_demo1.resturant.model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    boolean existsByCategoryNameIgnoreCase(@NotEmpty(message = "not_empty.categoryName")
                                   @Size(min = 7, max = 50, message = "size.categoryName") String categoryName);
}
