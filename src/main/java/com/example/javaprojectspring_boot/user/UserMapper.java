package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.contact.ContactMapper;
import com.example.javaprojectspring_boot.group.GroupMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class UserMapper {

    @Autowired
    protected GroupMapper groupMapper;
    @Autowired
    protected ContactMapper contactMapper;

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "role")
    @Mapping(ignore = true, target = "contacts")
    @Mapping(ignore = true, target = "groups")
    public abstract User toEntity(UserDto dto);


    @Mapping(ignore = true, target = "contacts")
    @Mapping(ignore = true, target = "groups")
    public abstract UserDto toDto(User user);

    @Mapping(ignore = true, target = "contacts")
    @Mapping(target = "groups", expression = "java(user.getGroups().stream().map(this.groupMapper::toDto).toList())")
    public abstract UserDto toDtoWithGroup(User user);

    @Mapping(ignore = true, target = "groups")
    @Mapping(target = "contacts", expression = "java(user.getContacts().stream().map(this.contactMapper::toDto).toList())")
    public abstract UserDto toDtoWithContact(User user);


    @Mapping(target = "contacts", expression = "java(user.getContacts().stream().map(this.contactMapper::toDto).toList())")
    @Mapping(target = "groups", expression = "java(user.getGroups().stream().map(this.groupMapper::toDto).toList())")
    public abstract UserDto toDtoWithContactAndGroup(User user);


    @Mapping(ignore = true, target = "role")
    @Mapping(ignore = true, target = "contacts")
    @Mapping(ignore = true, target = "groups")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserDto dto);


    public void view(User user, UserDto dto) {
        dto.setContacts(user.getContacts().stream().map(this.contactMapper::toDto).toList());
        dto.setGroups(user.getGroups().stream().map(this.groupMapper::toDto).toList());
    }

}

  /*      if (dto==null){
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
    }*/

