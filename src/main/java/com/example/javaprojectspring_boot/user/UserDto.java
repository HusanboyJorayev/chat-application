package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.chat.Chat;
import com.example.javaprojectspring_boot.chat.ChatDto;
import com.example.javaprojectspring_boot.contact.Contact;
import com.example.javaprojectspring_boot.contact.ContactDto;
import com.example.javaprojectspring_boot.group.Group;
import com.example.javaprojectspring_boot.group.GroupDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements UserDetails {

    private Integer id;
    private String firstName;
    private String lastName;
    @Column(updatable = false)
    private String phoneNumber;
    private String password;
    private String key1;
    private String key2;
    private boolean addGroup;

    private Integer groupId;

    private List<ContactDto> contacts;
    private List<GroupDto> groups;
    private List<ChatDto> messages;

    @Enumerated(EnumType.STRING)
    private Role role;



    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

