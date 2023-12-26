package com.example.javaprojectspring_boot.auth;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthValidation {

    public List<ErrorDto>validate(AuthenticateRequest request){
        List<ErrorDto>list=new ArrayList<>();

        if (StringUtils.isBlank(request.getPhoneNumber())){
            list.add(new ErrorDto("phoneNumber","phoneNumber cannot be null or empty"));
        }
        if (StringUtils.isBlank(request.getPassword())){
            list.add(new ErrorDto("Password","Password cannot be null or empty"));
        }

        return list;
    }
}
