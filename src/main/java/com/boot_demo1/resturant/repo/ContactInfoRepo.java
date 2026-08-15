package com.boot_demo1.resturant.repo;

import com.boot_demo1.resturant.model.ContactInfo;
import jakarta.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfo, Long> {
    boolean existsByEmailIgnoreCase(@NotBlank( message = "Email is required") String email);
}
