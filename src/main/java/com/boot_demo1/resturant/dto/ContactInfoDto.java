package com.boot_demo1.resturant.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDto {

    private Long id;

    @NotBlank( message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank( message = "Email is required")
    @Email( message = "Email should be valid")
    private String email;

    @NotBlank( message = "Subject is required")
    @Size(max = 150,  message = "Subject must not exceed 150 characters")
    private String subject;

    @NotBlank( message = "Message is required")
    private String message;




}