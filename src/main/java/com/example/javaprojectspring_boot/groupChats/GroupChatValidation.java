package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupChatValidation {

    public List<ErrorDto> validate(GroupChatDto dto) {

        List<ErrorDto> error = new ArrayList<>();
        if (dto.getGroupId() == null) {
            error.add(new ErrorDto("groupId", "groupId cannot be null"));
        }
        return error;
    }
}
