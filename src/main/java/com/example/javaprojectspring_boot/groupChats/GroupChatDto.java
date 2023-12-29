package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.user.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class GroupChatDto {
    private Integer id;
    private String message;
    @NotNull(message = "groupId cannot be null")
    private Integer groupId;

    private List<UserDto> usersList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
