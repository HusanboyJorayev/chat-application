package com.example.javaprojectspring_boot.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private AudioResponse media; //AudioResponse klassini nomini Media qilsa yaxshi bo'ladi hamma medialar shu formatda saqlanadigan bo'ladi backenda byteArray ko'rinishida
    private boolean request;
    private String getPhone;
    private String sendPhone;

    private Integer userId;
    private Integer groupId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
