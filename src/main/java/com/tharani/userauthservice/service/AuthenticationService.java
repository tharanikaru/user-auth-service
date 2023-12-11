package com.tharani.userauthservice.service;

import com.tharani.userauthservice.dto.JwtAuthenticationResponse;
import com.tharani.userauthservice.dto.SignInRequest;
import com.tharani.userauthservice.dto.SignUpRequest;
import com.tharani.userauthservice.repository.UserRepository;
import com.tharani.userauthservice.repository.model.Role;
import com.tharani.userauthservice.repository.model.User;
import com.tharani.userauthservice.service.JwtService;
import com.tharani.userauthservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .status(true)
                .token(jwt).build();
    }


    public JwtAuthenticationResponse signin(SignInRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder()
                    .status(true)
                    .message("Log in successfully")
                    .token(jwt).build();
        } catch (Exception e) {
            return  JwtAuthenticationResponse.builder()
                    .status(false)
                    .message(e.getMessage())
                    .build();
        }
    }

}