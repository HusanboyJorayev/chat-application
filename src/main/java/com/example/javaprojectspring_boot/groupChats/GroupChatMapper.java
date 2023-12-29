package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.user.UserMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class GroupChatMapper {

    @Autowired
    @Lazy
    protected UserMapper userMapper;

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "usersList")
    public abstract GroupChat toEntity(GroupChatDto dto);


    @Mapping(ignore = true, target = "usersList")
    public abstract GroupChatDto toDto(GroupChat groupChat);

    @Mapping(target = "usersList",expression = "java(groupChat.getUsersList().stream().map(this.userMapper::toDto).toList())")
    public abstract GroupChatDto toDtoWithUser(GroupChat groupChat);


    @Mapping(ignore = true, target = "usersList")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget GroupChat groupChat, GroupChatDto dto);


    public void view(GroupChat groupChat,GroupChatDto dto){
        dto.setUsersList(groupChat.getUsersList().stream().map(this.userMapper::toDto).toList());
    }
}
