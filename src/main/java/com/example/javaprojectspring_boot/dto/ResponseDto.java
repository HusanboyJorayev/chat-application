package com.example.javaprojectspring_boot.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto <T>{
    private int code;
    private String message;
    private boolean success;
    private T data;
    private List<ErrorDto>error;
}
