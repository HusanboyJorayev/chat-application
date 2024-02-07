package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.chat.ChatDto;
import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import com.example.javaprojectspring_boot.user.*;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements SimpleCrud<Integer, GroupDto> {
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final GroupValidation groupValidation;
    private final GroupRepository groupRepository;

    @Override
    public ResponseDto<GroupDto> create(GroupDto dto) {
        List<ErrorDto> errors = this.groupValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<GroupDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        try {
            Group group = this.groupMapper.toEntity(dto);
            group.setCreatedAt(LocalDateTime.now());
            group.setGroupRole(GroupRole.ADMIN);
            this.groupRepository.save(group);

            return ResponseDto.<GroupDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.groupMapper.toDto(group))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("Type while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupDto> get(Integer id) {
        try {
            return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                    .map(group -> ResponseDto.<GroupDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.groupMapper.toDto(group))
                            .build())
                    .orElse(ResponseDto.<GroupDto>builder()
                            .code(-1)
                            .message("group is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("group while getting error")
                    .build();
        }
    }
    public ResponseDto<List<GroupDto>> getWithUserAddedGroup(Integer id) {
        try {
            List<Group> groupList = this.groupRepository.getUserWithAddedGroup(id);
            if (groupList.isEmpty()) {
                return ResponseDto.<List<GroupDto>>builder()
                        .code(-1)
                        .message("Groups are not found")
                        .build();
            }
            return ResponseDto.<List<GroupDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(groupList.stream().map(this.groupMapper::toDto).toList())
                    .build();

        } catch (Exception e) {
            return ResponseDto.<List<GroupDto>>builder()
                    .code(-1)
                    .message("group while getting error")
                    .build();
        }
    }


    @Override
    public ResponseDto<GroupDto> update(GroupDto dto, Integer id) {
        return null;
    }

    public ResponseDto<GroupDto> getGroupChats(Integer id) {
        try {
            return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                    .map(group -> ResponseDto.<GroupDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.groupMapper.toDtoWithGroupChats(group))
                            .build())
                    .orElse(ResponseDto.<GroupDto>builder()
                            .code(-1)
                            .message("group is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("group while getting error")
                    .build();
        }
    }

    public ResponseDto<GroupDto> updateGroup(GroupDto dto, Integer id, Integer userId) {
        List<ErrorDto> errors = this.groupValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<GroupDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        List<User> userList = this.userRepository.deleteGroup(userId);
        if (userList.isEmpty()) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("you cannot update this group")
                    .build();
        }
        try {
            return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                    .map(group -> {
                        //checkUser(group.getUserId());
                        group.setUpdatedAt(LocalDateTime.now());
                        this.groupMapper.update(group, dto);
                        this.groupRepository.save(group);

                        return ResponseDto.<GroupDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.groupMapper.toDto(group))
                                .build();
                    })
                    .orElse(ResponseDto.<GroupDto>builder()
                            .code(-1)
                            .message("group is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("group while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupDto> delete(Integer id) {
        try {
            Optional<Group> optional = this.groupRepository.findByIdAndDeletedAtIsNull(id);
            List<User> userList = this.userRepository.deleteGroup(id);
            if (userList.isEmpty()) {
                return ResponseDto.<GroupDto>builder()
                        .code(-1)
                        .message("you cannot delete this group")
                        .build();
            }
            Group group = optional.get();
            group.setDeletedAt(LocalDateTime.now());
            return ResponseDto.<GroupDto>builder()
                    .success(true)
                    .message("Group delete successfully")
                    .data(this.groupMapper.toDto(group))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("group while deleting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<List<GroupDto>> getAll() {
        try {
            List<Group> allChats = this.groupRepository.getAllGroups();
            if (allChats.isEmpty()) {
                return ResponseDto.<List<GroupDto>>builder()
                        .code(-1)
                        .message("groups are not found")
                        .build();
            }
            return ResponseDto.<List<GroupDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(allChats.stream().map(this.groupMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<GroupDto>>builder()
                    .code(-1)
                    .message("groups while getting  all error")
                    .build();
        }
    }

    public ResponseDto<List<UserDto>> getAllWithUsers(Integer id) {
        try {
            List<User> groupWithUsers = this.groupRepository.getAllGroupWithUsers(id);
            if (groupWithUsers.isEmpty()) {
                return ResponseDto.<List<UserDto>>builder()
                        .code(-1)
                        .message("groups are not found")
                        .build();
            }
            return ResponseDto.<List<UserDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(groupWithUsers.stream().map(this.userMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<UserDto>>builder()
                    .code(-1)
                    .message("groups while getting  all error")
                    .build();
        }
    }

    public String addUserToGroup(Integer userId, Integer groupId) {

        List<User> userList = this.userRepository.addUser(userId);
        List<Group> groupList = this.groupRepository.addGroup(groupId);
        if (userList.isEmpty()) {
            return "You don't have this contact";
        }
        if (groupList.isEmpty()) {
            return "Group is not found";
        }
        int count = 0;
        for (Group group : groupList) {
            if (!group.getUserId().equals(userId) && group.getAddGroupId() == null) {
                count++;
                group.setAddGroupId(userId);
                this.groupRepository.save(group);
            }
        }
        if (count == 0) {
            return "you have already this group";
        }
        return "User added successfully";
    }

    public void checkUser(Integer id) {
        Optional<Group> optional = this.groupRepository.findByIdAndDeletedAtIsNull(id);
        var group = optional.get();
        if (!group.getGroupRole().equals(GroupRole.ADMIN)) {
            System.out.println(("you cannot update,delete and add User this group"));
        }
    }
}

