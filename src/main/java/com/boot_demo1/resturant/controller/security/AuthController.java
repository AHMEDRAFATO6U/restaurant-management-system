package com.boot_demo1.resturant.controller.security;

import com.boot_demo1.resturant.dto.security.AuthResponse;
import com.boot_demo1.resturant.dto.security.LoginRequest;
import com.boot_demo1.resturant.dto.security.RegisterRequest;
import com.boot_demo1.resturant.service.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {


    private final AuthService authService;


    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account with default ROLE_WAITER"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or email already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {

        AuthResponse response= authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }



    @Operation(
            summary = "Login user",
            description = "Authenticates user and returns JWT token"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);


    }





    @Operation(
            summary = "Logout user",
            description = "Invalidates the current JWT token (client-side)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logout successful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String ,String>> logout( @RequestHeader("Authorization") String token) {

        authService.logout(token);
        Map<String ,String> response = new HashMap<>();
        response.put("Message", "Logout successful");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }







    @Operation(
            summary = "Get all users (Admin only)",
            description = "Returns list of all registered users"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved"),
            @ApiResponse(responseCode = "403", description = "Forbidden - ADMIN required"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuthResponse>> getAllUsers() {
       List<AuthResponse> users =authService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @Operation(
            summary = "Delete user (Admin only)",
            description = "Deletes a user account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden - ADMIN required"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable Long userId) {
        authService.deleteUser(userId);
        Map<String,String> response = new HashMap<>();
        response.put("Message", "User deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }






}