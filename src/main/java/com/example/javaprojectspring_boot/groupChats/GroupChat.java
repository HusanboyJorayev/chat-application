package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groupChat")
public class GroupChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private Integer groupId;

    @OneToMany(mappedBy = "groupChatId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> usersList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
