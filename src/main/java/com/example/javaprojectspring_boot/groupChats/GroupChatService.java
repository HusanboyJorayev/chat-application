package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.contact.Contact;
import com.example.javaprojectspring_boot.contact.ContactDto;
import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupChatService implements SimpleCrud<Integer, GroupChatDto> {
    private final GroupChatRepository groupChatRepository;
    private final GroupChatValidation groupChatValidation;
    private final GroupChatMapper groupChatMapper;

    @Override
    public ResponseDto<GroupChatDto> create(GroupChatDto dto) {
        List<ErrorDto> errors = this.groupChatValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<GroupChatDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        try {
            GroupChat groupChat = this.groupChatMapper.toEntity(dto);
            groupChat.setCreatedAt(LocalDateTime.now());
            this.groupChatRepository.save(groupChat);

            return ResponseDto.<GroupChatDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.groupChatMapper.toDto(groupChat))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<GroupChatDto>builder()
                    .code(-1)
                    .message("groupChat while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupChatDto> get(Integer id) {
        try {
            return this.groupChatRepository.findByIdAndDeletedAtIsNull(id)
                    .map(groupChat -> ResponseDto.<GroupChatDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.groupChatMapper.toDto(groupChat))
                            .build())
                    .orElse(ResponseDto.<GroupChatDto>builder()
                            .code(-1)
                            .message("groupChat is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupChatDto>builder()
                    .code(-1)
                    .message("groupChat while getting error")
                    .build();
        }
    }
    public ResponseDto<GroupChatDto> getWithUsers(Integer id) {
        try {
            return this.groupChatRepository.findByIdAndDeletedAtIsNull(id)
                    .map(groupChat -> ResponseDto.<GroupChatDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.groupChatMapper.toDtoWithUser(groupChat))
                            .build())
                    .orElse(ResponseDto.<GroupChatDto>builder()
                            .code(-1)
                            .message("groupChat is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupChatDto>builder()
                    .code(-1)
                    .message("groupChat while getting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupChatDto> update(GroupChatDto dto, Integer id) {
        List<ErrorDto> errors = this.groupChatValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<GroupChatDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        try {
            return this.groupChatRepository.findByIdAndDeletedAtIsNull(id)
                    .map(groupChat -> {
                        groupChat.setUpdatedAt(LocalDateTime.now());
                        this.groupChatMapper.update(groupChat, dto);
                        this.groupChatRepository.save(groupChat);

                        return ResponseDto.<GroupChatDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.groupChatMapper.toDto(groupChat))
                                .build();
                    })
                    .orElse(ResponseDto.<GroupChatDto>builder()
                            .code(-1)
                            .message("groupChat is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupChatDto>builder()
                    .code(-1)
                    .message("groupChat while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupChatDto> delete(Integer id) {
        try {
            return this.groupChatRepository.findByIdAndDeletedAtIsNull(id)
                    .map(groupChat -> {
                        groupChat.setDeletedAt(LocalDateTime.now());
                        this.groupChatRepository.delete(groupChat);
                        return ResponseDto.<GroupChatDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.groupChatMapper.toDto(groupChat))
                                .build();
                    })
                    .orElse(ResponseDto.<GroupChatDto>builder()
                            .code(-1)
                            .message("groupChat is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<GroupChatDto>builder()
                    .code(-1)
                    .message("groupChat while deleting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<List<GroupChatDto>> getAll() {
        try {
            List<GroupChat> categories = this.groupChatRepository.getAllGroupChat();
            if (categories.isEmpty()) {
                return ResponseDto.<List<GroupChatDto>>builder()
                        .code(-1)
                        .message("groupChat are not found")
                        .build();
            }
            return ResponseDto.<List<GroupChatDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(categories.stream().map(this.groupChatMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<GroupChatDto>>builder()
                    .code(-1)
                    .message("groupChat while getting all")
                    .build();
        }
    }
}
