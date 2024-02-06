package com.example.javaprojectspring_boot.auth;


import com.example.javaprojectspring_boot.config.JwtService;
import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.token.Token;
import com.example.javaprojectspring_boot.token.TokenRepository;
import com.example.javaprojectspring_boot.token.TokenType;
import com.example.javaprojectspring_boot.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthValidation authValidation;
    private final UserValidation userValidation;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {

        List<ErrorDto> errors = this.userValidation.validate(request);
        if (!errors.isEmpty()) {
            return null;
        }

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .build();
        UserDto dto=this.userMapper.toDto(user);
        this.userRepository.save(user);
        var jwt = jwtService.generateToken(dto);


        return AuthenticationResponse.builder()
                .token("Successful")
                .build();
    }

    public UserDto authenticate(AuthenticateRequest request) {
        List<ErrorDto> errors = this.authValidation.validate(request);
        if (!errors.isEmpty()) {
            return null;
        }

       /* authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );*/
        var user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow();
        UserDto dto=this.userMapper.toDto(user);
        var jwt = jwtService.generateToken(dto);
        saveUserToken(user, jwt);
        return dto;
    }

    private void saveUserToken(User savedUser, String jwt) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwt)
                .tokenType(TokenType.BEARER)
                .build();

        this.tokenRepository.save(token);
    }

}
