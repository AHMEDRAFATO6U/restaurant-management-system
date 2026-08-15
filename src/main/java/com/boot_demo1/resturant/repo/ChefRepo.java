package com.boot_demo1.resturant.repo;


import com.boot_demo1.resturant.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepo extends JpaRepository<Chef, Long> {
    boolean existsByNameIgnoreCase(String name);
}
