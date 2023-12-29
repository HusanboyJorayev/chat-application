package com.example.javaprojectspring_boot.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDto {
    private Integer id;
    private String message;
    private boolean request;
    @NotBlank(message = "getPhone cannot be null or empty")
    private String getPhone;
    @NotBlank(message = "sendPhone cannot be null or empty")
    private String sendPhone;

    private Integer userId;
    private Integer groupId;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
