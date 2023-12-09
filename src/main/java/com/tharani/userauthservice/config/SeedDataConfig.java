package com.tharani.userauthservice.config;

import com.tharani.userauthservice.repository.UserRepository;
import com.tharani.userauthservice.repository.model.Role;
import com.tharani.userauthservice.repository.model.User;
import com.tharani.userauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = User
                    .builder()
                    .firstName("tharani")
                    .lastName("karunarathne")
                    .email("tharani@admin.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ADMIN)
                    .build();

            userService.save(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }
}
