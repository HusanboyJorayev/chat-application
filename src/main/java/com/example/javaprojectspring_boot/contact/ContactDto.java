package com.example.javaprojectspring_boot.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDto {
    private Long id;
    @NotBlank(message = "phoneNumber cannot be null or empty")
    private String phoneNumber;
    @NotBlank(message = "username cannot be null or empty")
    private String username;
    private Integer userId;
    private Integer addGroupId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
