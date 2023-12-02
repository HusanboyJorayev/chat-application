package com.example.javaprojectspring_boot.contact;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactValidation {

    public List<ErrorDto> validate(ContactDto dto) {

        List<ErrorDto> error = new ArrayList<>();
        if (StringUtils.isBlank(dto.getUsername())) {
            error.add(new ErrorDto("username", "username cannot be null or empty"));
        }

        if (StringUtils.isBlank(dto.getPhoneNumber())) {
            error.add(new ErrorDto("phoneNumber", "phoneNumber cannot be null or empty"));
        }
        return error;
    }
}
