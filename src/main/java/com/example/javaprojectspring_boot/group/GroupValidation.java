package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupValidation {

    public List<ErrorDto> validate(GroupDto dto) {

        List<ErrorDto> error = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())) {
            error.add(new ErrorDto("groupName", "groupName cannot be null or empty"));
        }

        if (dto.getUserId() == null) {
            error.add(new ErrorDto("userId", "userId cannot be null "));
        }
        return error;
    }
}
