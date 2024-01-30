package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.chat.ChatMapper;
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
    @Autowired
    protected ChatMapper chatMapper;

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "role")
    @Mapping(ignore = true, target = "contacts")
    @Mapping(ignore = true, target = "groups")
    @Mapping(ignore = true, target = "messages")
    public abstract User toEntity(UserDto dto);


    @Mapping(ignore = true, target = "contacts")
    @Mapping(ignore = true, target = "groups")
    @Mapping(ignore = true, target = "messages")
    public abstract UserDto toDto(User user);

    @Mapping(ignore = true, target = "contacts")
    @Mapping(ignore = true, target = "messages")
    @Mapping(target = "groups", expression = "java(user.getGroups().stream().map(this.groupMapper::toDto).toList())")
    public abstract UserDto toDtoWithGroup(User user);


    @Mapping(ignore = true, target = "contacts")
    @Mapping(ignore = true, target = "groups")
    @Mapping(target = "messages", expression = "java(user.getMessages().stream().map(this.chatMapper::toDto).toList())")
    public abstract UserDto toDtoWithChat(User user);

    @Mapping(ignore = true, target = "groups")
    @Mapping(ignore = true, target = "messages")
    @Mapping(target = "contacts", expression = "java(user.getContacts().stream().map(this.contactMapper::toDto).toList())")
    public abstract UserDto toDtoWithContact(User user);


    @Mapping(target = "contacts", expression = "java(user.getContacts().stream().map(this.contactMapper::toDto).toList())")
    @Mapping(target = "groups", expression = "java(user.getGroups().stream().map(this.groupMapper::toDtoWithGroupChats).toList())")
    @Mapping(target = "messages", expression = "java(user.getMessages().stream().map(this.chatMapper::toDto).toList())")
    public abstract UserDto toDtoWithContactAndGroupAndChat(User user);


    @Mapping(ignore = true, target = "role")
    @Mapping(ignore = true, target = "contacts")
    @Mapping(ignore = true, target = "groups")
    @Mapping(ignore = true, target = "messages")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserDto dto);


    public void view(User user, UserDto dto) {
        dto.setContacts(user.getContacts().stream().map(this.contactMapper::toDto).toList());
        dto.setGroups(user.getGroups().stream().map(this.groupMapper::toDtoWithGroupChats).toList());
        dto.setMessages(user.getMessages().stream().map(this.chatMapper::toDto).toList());
    }

}


