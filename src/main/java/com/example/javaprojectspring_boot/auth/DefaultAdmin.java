package com.example.javaprojectspring_boot.auth;


import com.example.javaprojectspring_boot.token.TokenRepository;
import com.example.javaprojectspring_boot.user.Role;
import com.example.javaprojectspring_boot.user.User;
import com.example.javaprojectspring_boot.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DefaultAdmin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;


    @PostConstruct
    public void saveAdmin() {

        userRepository.findByPhoneNumber("+998941490908").ifPresent(userRepository::delete);

        User admin = User.builder()
                .firstName("admin")
                .lastName("admin")
                //.email("admin@admin.com")
                .phoneNumber("+998941490908")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ROLE_ADMIN)
                .createdAt(LocalDateTime.now())
                .build();

        this.userRepository.save(admin);


        System.out.println("Admin created");
    }

}
