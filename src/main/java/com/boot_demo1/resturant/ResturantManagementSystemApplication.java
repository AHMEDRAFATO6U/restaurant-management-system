package com.boot_demo1.resturant;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ResturantManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResturantManagementSystemApplication.class, args);
        System.out.println("Restaurant Management System Application Started Successfully!");
    }






}
