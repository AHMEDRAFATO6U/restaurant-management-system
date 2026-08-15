package com.boot_demo1.resturant.config;

import com.boot_demo1.resturant.model.security.Role;
import com.boot_demo1.resturant.model.security.User;
import com.boot_demo1.resturant.repo.security.RoleRepository;
import com.boot_demo1.resturant.repo.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Create roles
        String[] roleNames = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_WAITER", "ROLE_CUSTOMER"};
        for (String name : roleNames) {
            if (!roleRepository.existsByName(name)) {
                roleRepository.save(new Role(name));
                System.out.println(" Created role: " + name);
            }
        }

        // Create admin user
        if (!userRepository.existsByEmail("admin@restaurant.com")) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new IllegalStateException("Admin role not found"));

            User admin = User.builder()
                    .email("admin@restaurant.com")
                    .fullName("System Administrator")
                    .password(passwordEncoder.encode("Admin@123"))
                    .roles(Set.of(adminRole))
                    .accountNonLocked(true)
                    .enabled(true)
                    .build();

            userRepository.save(admin);
            System.out.println(" Admin created: admin@restaurant.com / Admin@123");
        }
    }
}