package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.user.UserMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class GroupChatMapper {

   /* @Autowired
    @Lazy
    protected UserMapper;*/

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract GroupChat toEntity(GroupChatDto dto);


    public abstract GroupChatDto toDto(GroupChat groupChat);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget GroupChat groupChat, GroupChatDto dto);


    public void view(GroupChat groupChat,GroupChatDto dto){
    }
}
