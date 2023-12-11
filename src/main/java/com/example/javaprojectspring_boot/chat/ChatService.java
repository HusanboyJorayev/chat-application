package com.example.javaprojectspring_boot.chat;

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
                    .message("Type while saving error")
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
                            .message("type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ChatDto>builder()
                    .code(-1)
                    .message("Type while getting error")
                    .build();
        }
    }


    public ResponseDto<ChatDto> getSenderId(Integer id) {
        try {
            return this.chatRepository.findBySenderIdAndAndDeletedAtIsNull(id)
                    .map(chat -> ResponseDto.<ChatDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.chatMapper.toDto(chat))
                            .build())
                    .orElse(ResponseDto.<ChatDto>builder()
                            .code(-1)
                            .message("type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ChatDto>builder()
                    .code(-1)
                    .message("Type while getting error")
                    .build();
        }
    }


    public ResponseDto<ChatDto> getReceivedId(Integer id) {
        try {
            return this.chatRepository.findByGetterIdAndAndDeletedAtIsNull(id)
                    .map(chat -> ResponseDto.<ChatDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.chatMapper.toDto(chat))
                            .build())
                    .orElse(ResponseDto.<ChatDto>builder()
                            .code(-1)
                            .message("type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ChatDto>builder()
                    .code(-1)
                    .message("Type while getting error")
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
                    .message("type while updating error")
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
                    .message("Type while deleting error")
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
                    .message("Types while getting all")
                    .build();
        }
    }
}
