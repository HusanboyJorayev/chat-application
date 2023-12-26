package com.example.javaprojectspring_boot.user;

import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public void update(User user,UserDto dto){

        if (dto==null){
            return;
        }
        if (dto.getFirstName()!=null){
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName()!=null){
            user.setLastName(dto.getLastName());
        }
        if (dto.getPassword()!=null){
            user.setPassword(dto.getPassword());
        }
        if (dto.getKey1()!=null){
            user.setKey1(dto.getKey1());
        }
        if (dto.getKey2()!=null){
            user.setKey2(dto.getKey2());
        }
    }
}
