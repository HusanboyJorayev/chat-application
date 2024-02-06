package com.example.javaprojectspring_boot.groupChats;

import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T12:10:46+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class GroupChatMapperImpl extends GroupChatMapper {

    @Override
    public GroupChat toEntity(GroupChatDto dto) {
        if ( dto == null ) {
            return null;
        }

        GroupChat.GroupChatBuilder groupChat = GroupChat.builder();

        groupChat.message( dto.getMessage() );
        groupChat.groupId( dto.getGroupId() );

        return groupChat.build();
    }

    @Override
    public GroupChatDto toDto(GroupChat groupChat) {
        if ( groupChat == null ) {
            return null;
        }

        GroupChatDto.GroupChatDtoBuilder groupChatDto = GroupChatDto.builder();

        groupChatDto.id( groupChat.getId() );
        groupChatDto.message( groupChat.getMessage() );
        groupChatDto.groupId( groupChat.getGroupId() );
        groupChatDto.createdAt( groupChat.getCreatedAt() );
        groupChatDto.updatedAt( groupChat.getUpdatedAt() );
        groupChatDto.deletedAt( groupChat.getDeletedAt() );

        return groupChatDto.build();
    }

    @Override
    public void update(GroupChat groupChat, GroupChatDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            groupChat.setId( dto.getId() );
        }
        if ( dto.getMessage() != null ) {
            groupChat.setMessage( dto.getMessage() );
        }
        if ( dto.getGroupId() != null ) {
            groupChat.setGroupId( dto.getGroupId() );
        }
        if ( dto.getCreatedAt() != null ) {
            groupChat.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            groupChat.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            groupChat.setDeletedAt( dto.getDeletedAt() );
        }
    }
}
