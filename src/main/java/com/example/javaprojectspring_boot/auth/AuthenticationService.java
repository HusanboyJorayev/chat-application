package com.example.javaprojectspring_boot.auth;


import com.example.javaprojectspring_boot.chat.ChatMapper;
import com.example.javaprojectspring_boot.config.JwtService;
import com.example.javaprojectspring_boot.contact.ContactMapper;
import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.group.GroupMapper;
import com.example.javaprojectspring_boot.token.Token;
import com.example.javaprojectspring_boot.token.TokenRepository;
import com.example.javaprojectspring_boot.token.TokenType;
import com.example.javaprojectspring_boot.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthValidation authValidation;
    private final UserValidation userValidation;
    private final UserRepository userRepository;
    private final ContactMapper contactMapper;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;
    private final ChatMapper chatMapper;
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
        this.userRepository.save(user);
        var jwt = jwtService.generateToken(user);


        return AuthenticationResponse.builder()
                .token("Successful")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        List<ErrorDto> errors = this.authValidation.validate(request);
        if (!errors.isEmpty()) {
            return null;
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        saveUserToken(user, jwt);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    private void saveUserToken(User savedUser, String jwt) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwt)
                .tokenType(TokenType.BEARER)
                .build();

        this.tokenRepository.save(token);
    }

    public ResponseEntity<User> get(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = optional.get();
        user.getGroups().stream().map(this.groupMapper::toDto);
        user.getContacts().stream().map(this.contactMapper::toDto);
        /*user.getChatSenderId().stream().map(this.chatMapper::toDto);
        user.getChatGetterId().stream().map(this.chatMapper::toDto);*/
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<User> update(Integer id, UserDto dto) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        var user = optional.get();

        if (!this.userRepository.findByPhone(dto.getPhoneNumber())) {
            return ResponseEntity.badRequest().body(null);
        }

        user.setUpdatedAt(LocalDateTime.now());
        this.userMapper.update(user, dto);

        this.userRepository.save(user);

        return ResponseEntity.ok().body(this.userRepository.save(user));
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = this.userRepository.getAllUsers();
        List<User> userList = new ArrayList<>();
        for (User user : list) {
            userList.add(user);
        }
        return ResponseEntity.ok().body(userList);
    }

}
