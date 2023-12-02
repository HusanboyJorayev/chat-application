package com.example.javaprojectspring_boot.chat;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ChatMapper {

    public abstract ChatDto toDto(Chat chat);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Chat toEntity(ChatDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Chat chat, ChatDto dto);
}
