package com.example.javaprojectspring_boot.chat;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatValidation {
    public List<ErrorDto> validate(ChatDto dto) {
        List<ErrorDto> error = new ArrayList<>();

        if (dto.getGetterId() == null) {
            error.add(new ErrorDto("getterId", "getterId cannot be null"));
        }
        if (dto.getGetterId() == null) {
            error.add(new ErrorDto("setterId", "setterId cannot be null"));
        }
        return error;
    }
}
