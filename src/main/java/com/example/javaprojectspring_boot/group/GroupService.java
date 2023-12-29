package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.chat.ChatDto;
import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import com.example.javaprojectspring_boot.user.*;
import lombok.RequiredArgsConstructor;
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

    @Override
    public ResponseDto<GroupDto> update(GroupDto dto, Integer id) {
        List<ErrorDto> errors = this.groupValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<GroupDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }

        try {
            return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                    .map(group -> {
                        checkUser(group.getUserId());
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
            return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                    .map(group -> {
                        checkUser(group.getUserId());
                        group.setDeletedAt(LocalDateTime.now());
                        this.groupRepository.delete(group);

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
                    .message("groups while getting  all")
                    .build();
        }
    }

/* public ResponseEntity<String> addUser(Integer userId, Integer groupId) {
        var group = this.groupRepository.findByIdAndDeletedAtIsNull(groupId);
        var optional = this.userRepository.addUser(userId);
        if (optional.isEmpty() || !optional.get().isAddGroup()) {
            return ResponseEntity.badRequest().body("You cannot add this user");
        }
        if (group.isEmpty()) {
            return ResponseEntity.badRequest().body("this group is not exist");
        }
        var g = group.get();
        g.setGroupRole(GroupRole.USER);
        g.setAddGroupId(userId);
        this.groupRepository.save(g);
        return ResponseEntity.ok().body("User add successfully");
    }*/

    public void checkUser(Integer id) {
        Optional<Group> optional = this.groupRepository.findByIdAndDeletedAtIsNull(id);
        var group = optional.get();
        if (!group.getGroupRole().equals(GroupRole.ADMIN)) {
            System.out.println(("you cannot update,delete and add User this group"));
        }
    }
}

