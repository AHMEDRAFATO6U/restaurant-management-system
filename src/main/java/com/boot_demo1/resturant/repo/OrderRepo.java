package com.boot_demo1.resturant.repo;

import com.boot_demo1.resturant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findByCode(String code);
    boolean existsByCode(String code);
}
