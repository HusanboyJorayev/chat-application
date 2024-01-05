package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.chat.Chat;
import com.example.javaprojectspring_boot.contact.Contact;
import com.example.javaprojectspring_boot.group.Group;
import com.example.javaprojectspring_boot.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User /*implements UserDetails*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String key1;
    private String key2;

    private boolean addGroup;
    private Integer groupChatId;


    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Group> groups;

    private List<User> chatUsersList;
    private List<General> general;//general listi chatUsersList va groups listlarini o'z ichiga olgan list bo'ladi, General klassi User va Group klasslarini o'z ichiga olgan bo'ladi

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<List<Chat>> messages;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

