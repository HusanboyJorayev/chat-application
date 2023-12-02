package com.example.javaprojectspring_boot.chat;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private Integer id;
    @NotNull(message = "senderid cannot be null")
    private Integer senderId;
    @NotNull(message = "getterId cannot be null")
    private Integer getterId;
    private String message;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
