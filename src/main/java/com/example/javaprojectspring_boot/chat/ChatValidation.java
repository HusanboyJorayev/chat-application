package com.example.javaprojectspring_boot.chat;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.user.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ChatValidation {

    private final UserRepository userRepository;

    public List<ErrorDto> validate(ChatDto dto) {
        List<ErrorDto> error = new ArrayList<>();

        if (Objects.equals(dto.getGetPhone(), dto.getSendPhone())) {
            error.add(new ErrorDto("getPhone and sendPhone", "getPhone and sendPhone cannot be equals"));
        }
        if (this.userRepository.findByPhoneNumber(dto.getGetPhone()).isEmpty()) {
            error.add(new ErrorDto("getPhone", "getPhone cannot be exist"));
        }
        if (this.userRepository.findByPhoneNumber(dto.getSendPhone()).isEmpty()) {
            error.add(new ErrorDto("SendPhone", "SendPhone cannot be exist"));
        }

        if (StringUtils.isBlank(dto.getSendPhone())) {
            error.add(new ErrorDto("SendPhone", "SendPhone cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getGetPhone())) {
            error.add(new ErrorDto("GetPhone", "GetPhone cannot be null or empty"));
        }

        return error;
    }
}
