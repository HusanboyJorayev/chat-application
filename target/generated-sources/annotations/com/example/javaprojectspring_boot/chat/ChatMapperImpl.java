package com.example.javaprojectspring_boot.chat;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-31T17:18:45+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class ChatMapperImpl extends ChatMapper {

    @Override
    public ChatDto toDto(Chat chat) {
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

    @Override
    public Chat toEntity(ChatDto dto) {
        if ( dto == null ) {
            return null;
        }

        Chat.ChatBuilder chat = Chat.builder();

        chat.message( dto.getMessage() );
        chat.request( dto.isRequest() );
        chat.getPhone( dto.getGetPhone() );
        chat.sendPhone( dto.getSendPhone() );
        chat.userId( dto.getUserId() );
        chat.groupId( dto.getGroupId() );

        return chat.build();
    }

    @Override
    public void update(Chat chat, ChatDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            chat.setId( dto.getId() );
        }
        if ( dto.getMessage() != null ) {
            chat.setMessage( dto.getMessage() );
        }
        chat.setRequest( dto.isRequest() );
        if ( dto.getGetPhone() != null ) {
            chat.setGetPhone( dto.getGetPhone() );
        }
        if ( dto.getSendPhone() != null ) {
            chat.setSendPhone( dto.getSendPhone() );
        }
        if ( dto.getUserId() != null ) {
            chat.setUserId( dto.getUserId() );
        }
        if ( dto.getGroupId() != null ) {
            chat.setGroupId( dto.getGroupId() );
        }
        if ( dto.getCreatedAt() != null ) {
            chat.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            chat.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            chat.setDeletedAt( dto.getDeletedAt() );
        }
    }
}
