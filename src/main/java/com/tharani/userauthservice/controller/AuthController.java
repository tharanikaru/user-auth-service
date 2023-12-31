package com.tharani.userauthservice.controller;

import com.tharani.userauthservice.dto.JwtAuthenticationResponse;
import com.tharani.userauthservice.dto.SignInRequest;
import com.tharani.userauthservice.dto.SignUpRequest;
import com.tharani.userauthservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j(topic = "[AuthController]")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        log.info("sign-in req {}", request);
        return authenticationService.signin(request);
    }


}
