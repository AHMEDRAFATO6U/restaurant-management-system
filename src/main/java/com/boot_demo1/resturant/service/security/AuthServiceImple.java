package com.boot_demo1.resturant.service.security;

import com.boot_demo1.resturant.dto.security.AuthResponse;
import com.boot_demo1.resturant.dto.security.LoginRequest;
import com.boot_demo1.resturant.dto.security.RegisterRequest;
import com.boot_demo1.resturant.jwt.JwtUtil;
import com.boot_demo1.resturant.model.security.Role;
import com.boot_demo1.resturant.model.security.User;
import com.boot_demo1.resturant.repo.security.RoleRepository;
import com.boot_demo1.resturant.repo.security.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final Map<String, LocalDateTime> blacklistedTokens = new ConcurrentHashMap<>();


    @Override
    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        Role defaultRole =roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalArgumentException("No role found"));

        User user =
                User.builder()
                        .fullName(request.getFullName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .accountNonLocked(true)
                        .enabled(true)
                        .build();

        user.getRoles().add(defaultRole);
        userRepository.save(user);


       UserDetails userDetails = buildUserDetails(user);
       String token = jwtUtil.generateToken(userDetails);

        return AuthResponse .builder()
                .token(token)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .message("USER REGISTERED SUCCESSFULLY")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

       User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email not found please register first"));

        UserDetails userDetails = buildUserDetails(user);
        String token = jwtUtil.generateToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .fullName(user.getFullName())
                .email(user.getEmail())
                .message("LOGIN DONE SUCCSSFULLY")
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();

    }

    @Transactional
    public void logout(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            long expirationTime = jwtUtil.getExpirationTime(jwt);
            if (expirationTime > 0) {
                blacklistedTokens.put(jwt, LocalDateTime.now().plusSeconds(expirationTime / 1000));
            }
        }
    }



    @Override
    public List<AuthResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            throw new SecurityException("User not found");
        }

        return users.stream()
                .map(user -> AuthResponse.builder()
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .message("User details")
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public void deleteUser(Long userId) {
        User user=userRepository.getById(userId);
        if(user==null) {
            throw new SecurityException("User not found");
        }
        userRepository.delete(user);

    }


    private UserDetails buildUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .accountLocked(!user.isAccountNonLocked())
                .disabled(!user.isEnabled())
                .authorities(
                        user.getRoles().stream()
                                .map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority(r.getName()))
                                .toList()
                )
                .build();
    }
}
