package com.example.javaprojectspring_boot.auth;


import com.example.javaprojectspring_boot.chat.ChatMapper;
import com.example.javaprojectspring_boot.config.JwtService;
import com.example.javaprojectspring_boot.contact.ContactMapper;
import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.token.Token;
import com.example.javaprojectspring_boot.token.TokenRepository;
import com.example.javaprojectspring_boot.token.TokenType;
import com.example.javaprojectspring_boot.user.Role;
import com.example.javaprojectspring_boot.user.User;
import com.example.javaprojectspring_boot.user.UserRepository;
import com.example.javaprojectspring_boot.user.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final UserValidation userValidation;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ContactMapper contactMapper;
    private final ChatMapper chatMapper;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {

        List<ErrorDto> errors = this.userValidation.validate(request);
        if (!errors.isEmpty()) {
            throw new RuntimeException(String.valueOf(List.of(errors)));
        }

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                //.email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .build();
        this.userRepository.save(user);
        //var savedUser = this.userRepository.save(user);
        var jwt = jwtService.generateToken(user);

        //saveUserToken(user, jwt);

        return AuthenticationResponse.builder()
                .token("Successful")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        //revokeAllUsertokens(user);
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
                .expired(true)
                .revoked(true)
                .build();

        this.tokenRepository.save(token);
    }

   /* private void revokeAllUsertokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }*/

    public ResponseEntity<User> get(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            //return ResponseEntity.badRequest().body(null);
            throw new RuntimeException("User is not found");
        }
        User user = optional.get();
        user.getContacts().stream().map(this.contactMapper::toDto);
        user.getChatSenderId().stream().map(this.chatMapper::toDto);
        user.getChatGetterId().stream().map(this.chatMapper::toDto);
        return ResponseEntity.ok().body(user);
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
