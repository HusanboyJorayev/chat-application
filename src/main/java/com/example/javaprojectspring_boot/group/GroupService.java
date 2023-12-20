package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.chat.ChatDto;
import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService implements SimpleCrud<Integer, GroupDto> {
    private final GroupMapper groupMapper;
    private final GroupValidation groupValidation;
    private final GroupRepository groupRepository;


    @Override
    public ResponseDto<GroupDto> create(GroupDto dto) {
        List<ErrorDto> errors = this.groupValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<GroupDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .build();
        }
        try {
            Group group = this.groupMapper.toEntity(dto);
            group.setCreatedAt(LocalDateTime.now());
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
                            .message("type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("Type while getting error")
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
                    .build();
        }
        try {
            return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                    .map(group -> {
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
                            .message("Type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("type while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupDto> delete(Integer id) {
        try {
            return this.groupRepository.findByIdAndDeletedAtIsNull(id)
                    .map(group -> {
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
                            .message("Type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupDto>builder()
                    .code(-1)
                    .message("Type while deleting error")
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
                        .message("Types are not found")
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
                    .message("Types while getting all")
                    .build();
        }
    }
}
