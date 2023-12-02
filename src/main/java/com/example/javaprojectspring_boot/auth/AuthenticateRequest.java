package com.example.javaprojectspring_boot.auth;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateRequest {
    private String password;
    private String phoneNumber;
}
