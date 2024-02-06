package com.example.javaprojectspring_boot.user;

import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T12:10:43+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( dto.getFirstName() );
        user.lastName( dto.getLastName() );
        user.phoneNumber( dto.getPhoneNumber() );
        user.password( dto.getPassword() );
        user.key1( dto.getKey1() );
        user.key2( dto.getKey2() );
        user.groupId( dto.getGroupId() );

        return user.build();
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.phoneNumber( user.getPhoneNumber() );
        userDto.password( user.getPassword() );
        userDto.key1( user.getKey1() );
        userDto.key2( user.getKey2() );
        userDto.groupId( user.getGroupId() );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        return userDto.build();
    }

    @Override
    public UserDto toDtoWithGroup(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.phoneNumber( user.getPhoneNumber() );
        userDto.password( user.getPassword() );
        userDto.key1( user.getKey1() );
        userDto.key2( user.getKey2() );
        userDto.groupId( user.getGroupId() );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.groups( user.getGroups().stream().map(this.groupMapper::toDto).toList() );

        return userDto.build();
    }

    @Override
    public UserDto toDtoWithChat(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.phoneNumber( user.getPhoneNumber() );
        userDto.password( user.getPassword() );
        userDto.key1( user.getKey1() );
        userDto.key2( user.getKey2() );
        userDto.groupId( user.getGroupId() );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.messages( user.getMessages().stream().map(this.chatMapper::toDto).toList() );

        return userDto.build();
    }

    @Override
    public UserDto toDtoWithContact(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.phoneNumber( user.getPhoneNumber() );
        userDto.password( user.getPassword() );
        userDto.key1( user.getKey1() );
        userDto.key2( user.getKey2() );
        userDto.groupId( user.getGroupId() );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.contacts( user.getContacts().stream().map(this.contactMapper::toDto).toList() );

        return userDto.build();
    }

    @Override
    public UserDto toDtoWithContactAndGroupAndChat(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.phoneNumber( user.getPhoneNumber() );
        userDto.password( user.getPassword() );
        userDto.key1( user.getKey1() );
        userDto.key2( user.getKey2() );
        userDto.groupId( user.getGroupId() );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.contacts( user.getContacts().stream().map(this.contactMapper::toDto).toList() );
        userDto.groups( user.getGroups().stream().map(this.groupMapper::toDtoWithGroupChats).toList() );
        userDto.messages( user.getMessages().stream().map(this.chatMapper::toDto).toList() );

        return userDto.build();
    }

    @Override
    public void update(User user, UserDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            user.setId( dto.getId() );
        }
        if ( dto.getFirstName() != null ) {
            user.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            user.setLastName( dto.getLastName() );
        }
        if ( dto.getPhoneNumber() != null ) {
            user.setPhoneNumber( dto.getPhoneNumber() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
        if ( dto.getKey1() != null ) {
            user.setKey1( dto.getKey1() );
        }
        if ( dto.getKey2() != null ) {
            user.setKey2( dto.getKey2() );
        }
        user.setAddGroup( dto.isAddGroup() );
        if ( dto.getGroupId() != null ) {
            user.setGroupId( dto.getGroupId() );
        }
        if ( dto.getCreatedAt() != null ) {
            user.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            user.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            user.setDeletedAt( dto.getDeletedAt() );
        }
    }
}
