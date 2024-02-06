package com.example.javaprojectspring_boot.group;

import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T12:10:43+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class GroupMapperImpl extends GroupMapper {

    @Override
    public Group toEntity(GroupDto dto) {
        if ( dto == null ) {
            return null;
        }

        Group.GroupBuilder group = Group.builder();

        group.name( dto.getName() );
        group.userId( dto.getUserId() );
        group.groupRole( dto.getGroupRole() );

        return group.build();
    }

    @Override
    public GroupDto toDto(Group groups) {
        if ( groups == null ) {
            return null;
        }

        GroupDto.GroupDtoBuilder groupDto = GroupDto.builder();

        groupDto.id( groups.getId() );
        groupDto.name( groups.getName() );
        groupDto.userId( groups.getUserId() );
        groupDto.groupRole( groups.getGroupRole() );
        groupDto.createdAt( groups.getCreatedAt() );
        groupDto.updatedAt( groups.getUpdatedAt() );
        groupDto.deletedAt( groups.getDeletedAt() );

        return groupDto.build();
    }

    @Override
    public GroupDto toDtoWithGroupChats(Group groups) {
        if ( groups == null ) {
            return null;
        }

        GroupDto.GroupDtoBuilder groupDto = GroupDto.builder();

        groupDto.id( groups.getId() );
        groupDto.name( groups.getName() );
        groupDto.userId( groups.getUserId() );
        groupDto.groupRole( groups.getGroupRole() );
        groupDto.createdAt( groups.getCreatedAt() );
        groupDto.updatedAt( groups.getUpdatedAt() );
        groupDto.deletedAt( groups.getDeletedAt() );

        groupDto.groupChats( groups.getGroupChats().stream().map(this.groupChatMapper::toDto).toList() );

        return groupDto.build();
    }

    @Override
    public void update(Group groups, GroupDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            groups.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            groups.setName( dto.getName() );
        }
        if ( dto.getUserId() != null ) {
            groups.setUserId( dto.getUserId() );
        }
        if ( dto.getGroupRole() != null ) {
            groups.setGroupRole( dto.getGroupRole() );
        }
        if ( dto.getAddGroupId() != null ) {
            groups.setAddGroupId( dto.getAddGroupId() );
        }
        if ( dto.getCreatedAt() != null ) {
            groups.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            groups.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            groups.setDeletedAt( dto.getDeletedAt() );
        }
    }
}
