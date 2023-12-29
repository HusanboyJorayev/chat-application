package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.user.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatDto {
    private Integer id;
    private String message;
    @NotBlank(message = "groupId cannot be null")
    private Integer groupId;

    private List<UserDto> usersList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
