package com.example.javaprojectspring_boot.group;


import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class GroupMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Group toEntity(GroupDto dto);


    public abstract GroupDto toDto(Group groups);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Group groups, GroupDto dto);
}
