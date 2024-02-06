package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.chat.Chat;
import com.example.javaprojectspring_boot.groupChats.GroupChat;
import com.example.javaprojectspring_boot.user.GroupRole;
import com.example.javaprojectspring_boot.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer userId;

    @Enumerated(EnumType.STRING)
    private GroupRole groupRole;

    private Integer addGroupId;


    @OneToMany(mappedBy = "groupId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GroupChat> groupChats;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
