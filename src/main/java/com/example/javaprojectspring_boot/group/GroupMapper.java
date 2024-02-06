package com.example.javaprojectspring_boot.group;


import com.example.javaprojectspring_boot.groupChats.GroupChatMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class GroupMapper {

    @Autowired
    protected GroupChatMapper groupChatMapper;


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "groupChats")
    public abstract Group toEntity(GroupDto dto);

    @Mapping(ignore = true, target = "groupChats")
    public abstract GroupDto toDto(Group groups);

    @Mapping(target = "groupChats", expression = "java(groups.getGroupChats().stream().map(this.groupChatMapper::toDto).toList())")
    public abstract GroupDto toDtoWithGroupChats(Group groups);

    @Mapping(ignore = true, target = "groupChats")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Group groups, GroupDto dto);

    public void view(Group groups, GroupDto dto) {
        dto.setGroupChats(groups.getGroupChats().stream().map(this.groupChatMapper::toDto).toList());
    }
}
