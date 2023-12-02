package com.example.javaprojectspring_boot.auth;

import com.example.javaprojectspring_boot.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/get")
    public ResponseEntity<User> get(@RequestParam Integer id) {
        return this.authenticationService.get(id);
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return this.authenticationService.getAllUsers();
    }
}
