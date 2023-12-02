package com.example.javaprojectspring_boot.contact;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class ContactMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Contact toEntity(ContactDto dto);


    public abstract ContactDto toDto(Contact contact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Contact contact, ContactDto dto);
}
