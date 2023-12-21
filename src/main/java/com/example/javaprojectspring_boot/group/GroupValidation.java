package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.user.User;
import com.example.javaprojectspring_boot.user.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GroupValidation {
    private final UserRepository userRepository;

    public List<ErrorDto> validate(GroupDto dto) {


        List<ErrorDto> error = new ArrayList<>();

        Optional<User> user = this.userRepository.findByIdAndDeletedAtIsNull(dto.getUserId());
        if (user.isEmpty()) {
            error.add(new ErrorDto("userId", "this userId cannot exit"));
        }

        if (StringUtils.isBlank(dto.getName())) {
            error.add(new ErrorDto("groupName", "groupName cannot be null or empty"));
        }

        if (dto.getUserId() == null) {
            error.add(new ErrorDto("userId", "userId cannot be null "));
        }
        return error;
    }
}
