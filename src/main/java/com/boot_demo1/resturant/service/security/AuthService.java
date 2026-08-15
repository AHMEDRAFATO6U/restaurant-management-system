package com.boot_demo1.resturant.service.security;

import com.boot_demo1.resturant.dto.security.AuthResponse;
import com.boot_demo1.resturant.dto.security.LoginRequest;
import com.boot_demo1.resturant.dto.security.RegisterRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface AuthService {
    AuthResponse register(@Valid RegisterRequest request);

    AuthResponse login(@Valid LoginRequest request);

    void logout(String token);

    List<AuthResponse> getAllUsers();

    void deleteUser(Long userId);
}
