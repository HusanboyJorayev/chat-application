package com.example.javaprojectspring_boot.group;


import com.example.javaprojectspring_boot.chat.Chat;
import com.example.javaprojectspring_boot.chat.ChatDto;
import com.example.javaprojectspring_boot.groupChats.GroupChat;
import com.example.javaprojectspring_boot.groupChats.GroupChatDto;
import com.example.javaprojectspring_boot.user.GroupRole;
import com.example.javaprojectspring_boot.user.User;
import com.example.javaprojectspring_boot.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDto {
    private Integer id;
    @NotBlank(message = "groupName cannot be null or empty")
    private String name;
    @NotNull(message = "userId cannot be null")
    private Integer userId;

    @Enumerated(EnumType.STRING)
    private GroupRole groupRole;

    private Integer addGroupId;

    private List<GroupChatDto> groupChats;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
