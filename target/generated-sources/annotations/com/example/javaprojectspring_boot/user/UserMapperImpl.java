package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.chat.Chat;
import com.example.javaprojectspring_boot.chat.ChatDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-30T19:14:07+0500",
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
        user.groupChatId( dto.getGroupChatId() );
        user.messages( chatDtoListToChatList( dto.getMessages() ) );

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
        userDto.groupChatId( user.getGroupChatId() );
        userDto.messages( chatListToChatDtoList( user.getMessages() ) );
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
        userDto.groupChatId( user.getGroupChatId() );
        userDto.messages( chatListToChatDtoList( user.getMessages() ) );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.groups( user.getGroups().stream().map(this.groupMapper::toDto).toList() );

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
        userDto.groupChatId( user.getGroupChatId() );
        userDto.messages( chatListToChatDtoList( user.getMessages() ) );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.contacts( user.getContacts().stream().map(this.contactMapper::toDto).toList() );

        return userDto.build();
    }

    @Override
    public UserDto toDtoWithContactAndGroup(User user) {
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
        userDto.groupChatId( user.getGroupChatId() );
        userDto.messages( chatListToChatDtoList( user.getMessages() ) );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.contacts( user.getContacts().stream().map(this.contactMapper::toDto).toList() );
        userDto.groups( user.getGroups().stream().map(this.groupMapper::toDtoWithGroupChats).toList() );

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
        if ( dto.getGroupChatId() != null ) {
            user.setGroupChatId( dto.getGroupChatId() );
        }
        if ( user.getMessages() != null ) {
            List<Chat> list = chatDtoListToChatList( dto.getMessages() );
            if ( list != null ) {
                user.getMessages().clear();
                user.getMessages().addAll( list );
            }
        }
        else {
            List<Chat> list = chatDtoListToChatList( dto.getMessages() );
            if ( list != null ) {
                user.setMessages( list );
            }
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

    protected Chat chatDtoToChat(ChatDto chatDto) {
        if ( chatDto == null ) {
            return null;
        }

        Chat.ChatBuilder chat = Chat.builder();

        chat.id( chatDto.getId() );
        chat.message( chatDto.getMessage() );
        chat.request( chatDto.isRequest() );
        chat.getPhone( chatDto.getGetPhone() );
        chat.sendPhone( chatDto.getSendPhone() );
        chat.userId( chatDto.getUserId() );
        chat.groupId( chatDto.getGroupId() );
        chat.createdAt( chatDto.getCreatedAt() );
        chat.updatedAt( chatDto.getUpdatedAt() );
        chat.deletedAt( chatDto.getDeletedAt() );

        return chat.build();
    }

    protected List<Chat> chatDtoListToChatList(List<ChatDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Chat> list1 = new ArrayList<Chat>( list.size() );
        for ( ChatDto chatDto : list ) {
            list1.add( chatDtoToChat( chatDto ) );
        }

        return list1;
    }

    protected ChatDto chatToChatDto(Chat chat) {
        if ( chat == null ) {
            return null;
        }

        ChatDto.ChatDtoBuilder chatDto = ChatDto.builder();

        chatDto.id( chat.getId() );
        chatDto.message( chat.getMessage() );
        chatDto.request( chat.isRequest() );
        chatDto.getPhone( chat.getGetPhone() );
        chatDto.sendPhone( chat.getSendPhone() );
        chatDto.userId( chat.getUserId() );
        chatDto.groupId( chat.getGroupId() );
        chatDto.createdAt( chat.getCreatedAt() );
        chatDto.updatedAt( chat.getUpdatedAt() );
        chatDto.deletedAt( chat.getDeletedAt() );

        return chatDto.build();
    }

    protected List<ChatDto> chatListToChatDtoList(List<Chat> list) {
        if ( list == null ) {
            return null;
        }

        List<ChatDto> list1 = new ArrayList<ChatDto>( list.size() );
        for ( Chat chat : list ) {
            list1.add( chatToChatDto( chat ) );
        }

        return list1;
    }
}
