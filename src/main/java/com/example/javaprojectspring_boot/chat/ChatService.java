package com.example.javaprojectspring_boot.chat;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatService implements SimpleCrud<Integer, ChatDto> {
    private final ChatValidation chatValidation;
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    @Override
    public ResponseDto<ChatDto> create(ChatDto dto) {
        List<ErrorDto> errors = this.chatValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ChatDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        if (Objects.equals(dto.getGetterId(), dto.getSenderId())) {
            return ResponseDto.<ChatDto>builder()
                    .code(-1)
                    .message("getterId cannot be equals setterId")
                    .build();
        }
        try {
            Chat chat = this.chatMapper.toEntity(dto);
            chat.setCreatedAt(LocalDateTime.now());
            this.chatRepository.save(chat);

            return ResponseDto.<ChatDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.chatMapper.toDto(chat))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ChatDto>builder()
                    .code(-1)
                    .message("chat while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<ChatDto> get(Integer id) {
        try {
            return this.chatRepository.findByIdAndDeletedAtIsNull(id)
                    .map(chat -> ResponseDto.<ChatDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.chatMapper.toDto(chat))
                            .build())
                    .orElse(ResponseDto.<ChatDto>builder()
                            .code(-1)
                            .message("chat is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ChatDto>builder()
                    .code(-1)
                    .message("chat while getting error")
                    .build();
        }
    }


    public ResponseDto<List<ChatDto>> getSenderId(Integer id) {

        try {
            List<Chat> allChats = this.chatRepository.getAllSenderIdAndDeletedAtIsNull(id);
            if (allChats.isEmpty()) {
                return ResponseDto.<List<ChatDto>>builder()
                        .code(-1)
                        .message("Types are not found")
                        .build();
            }
            return ResponseDto.<List<ChatDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(allChats.stream().map(this.chatMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ChatDto>>builder()
                    .code(-1)
                    .message("chat while getting all")
                    .build();
        }
    }


    public ResponseDto<List<ChatDto>> getReceivedId(Integer id) {

        try {
            List<Chat> allChats = this.chatRepository.getAllGetterIdAndDeletedAtIsNull(id);
            if (allChats.isEmpty()) {
                return ResponseDto.<List<ChatDto>>builder()
                        .code(-1)
                        .message("Types are not found")
                        .build();
            }
            return ResponseDto.<List<ChatDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(allChats.stream().map(this.chatMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ChatDto>>builder()
                    .code(-1)
                    .message("chat while getting all")
                    .build();
        }
    }

    @Override
    public ResponseDto<ChatDto> update(ChatDto dto, Integer id) {
        List<ErrorDto> errors = this.chatValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ChatDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        try {
            return this.chatRepository.findByIdAndDeletedAtIsNull(id)
                    .map(chat -> {
                        chat.setUpdatedAt(LocalDateTime.now());
                        this.chatMapper.update(chat, dto);
                        this.chatRepository.save(chat);

                        return ResponseDto.<ChatDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.chatMapper.toDto(chat))
                                .build();
                    })
                    .orElse(ResponseDto.<ChatDto>builder()
                            .code(-1)
                            .message("Type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ChatDto>builder()
                    .code(-1)
                    .message("chat while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<ChatDto> delete(Integer id) {
        try {
            return this.chatRepository.findByIdAndDeletedAtIsNull(id)
                    .map(chat -> {
                        chat.setDeletedAt(LocalDateTime.now());
                        this.chatRepository.delete(chat);

                        return ResponseDto.<ChatDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.chatMapper.toDto(chat))
                                .build();
                    })
                    .orElse(ResponseDto.<ChatDto>builder()
                            .code(-1)
                            .message("Type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ChatDto>builder()
                    .code(-1)
                    .message("chat while deleting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<List<ChatDto>> getAll() {
        try {
            List<Chat> allChats = this.chatRepository.getAllChats();
            if (allChats.isEmpty()) {
                return ResponseDto.<List<ChatDto>>builder()
                        .code(-1)
                        .message("Types are not found")
                        .build();
            }
            return ResponseDto.<List<ChatDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(allChats.stream().map(this.chatMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ChatDto>>builder()
                    .code(-1)
                    .message("chat while getting all")
                    .build();
        }
    }

    public ResponseDto<List<ChatDto>> getAllGetterIdAndSenderId(Integer getId, Integer sendId) {

        try {
            List<Chat> allChats = this.chatRepository.AllByGetterIdAndSenderIdAndDeletedAtIsNull(getId, sendId);
            if (allChats.isEmpty()) {
                return ResponseDto.<List<ChatDto>>builder()
                        .code(-1)
                        .message("chats are not found")
                        .build();
            }
            return ResponseDto.<List<ChatDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(allChats.stream().map(this.chatMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ChatDto>>builder()
                    .code(-1)
                    .message("chat while getting all")
                    .build();
        }

    }
}
