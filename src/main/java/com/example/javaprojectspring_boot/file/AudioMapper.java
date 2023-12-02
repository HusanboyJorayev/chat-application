package com.example.javaprojectspring_boot.file;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class AudioMapper {


    public abstract AudioResponse toDto(Audio audio);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Audio audio, AudioResponse request);

}
