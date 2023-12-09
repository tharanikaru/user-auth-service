package com.tharani.userauthservice.service;


import com.tharani.userauthservice.dto.AdminCreateRequest;
import com.tharani.userauthservice.dto.AdminCreateResponse;
import com.tharani.userauthservice.repository.UserRepository;
import com.tharani.userauthservice.repository.model.Role;
import com.tharani.userauthservice.repository.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "AdminService")
public class AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AdminCreateResponse adminCreate(AdminCreateRequest request) {
        val user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .createdAt(LocalDateTime.now())
                .build();
        val createdAdmin = userRepository.save(user);
        return AdminCreateResponse.builder()
                .id(createdAdmin.getId())
                .build();
    }
}
