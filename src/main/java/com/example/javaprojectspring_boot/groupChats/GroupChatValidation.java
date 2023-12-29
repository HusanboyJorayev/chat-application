package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupChatValidation {

    @Autowired
    private GroupRepository groupRepository;

    public List<ErrorDto> validate(GroupChatDto dto) {
        List<ErrorDto> error = new ArrayList<>();

        var optional = this.groupRepository.findByIdAndDeletedAtIsNull(dto.getGroupId());
        if (optional.isEmpty()) {
            error.add(new ErrorDto("groupId", "this group is not exist"));
        }

        if (dto.getGroupId() == null) {
            error.add(new ErrorDto("groupId", "groupId cannot be null"));
        }
        return error;
    }
}
