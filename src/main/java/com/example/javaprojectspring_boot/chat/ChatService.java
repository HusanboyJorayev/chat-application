package com.example.javaprojectspring_boot.chat;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import com.example.javaprojectspring_boot.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                            .message("chat is not found")
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
                            .message("chat is not found")
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

    public ResponseDto<List<ChatDto>> getAllGetPhoneAndSendPhone(String getPhone, String sendPhone) {
        try {
            List<Chat>chats=this.chatRepository.getAllChats();
            if (chats.isEmpty()) {
                return ResponseDto.<List<ChatDto>>builder()
                        .code(-1)
                        .message("chats are not found")
                        .build();
            }
            List<Chat>chattingUsersMessage=new ArrayList<>();
            for (Chat chat : chats) {
                if ((chat.getGetPhone().equals(getPhone)&&chat.getSendPhone().equals(sendPhone))
                   || (chat.getSendPhone().equals(getPhone)&&chat.getGetPhone().equals(sendPhone))
                ){
                    chattingUsersMessage.add(chat);
                }
            }

            return ResponseDto.<List<ChatDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(chattingUsersMessage.stream().map(this.chatMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ChatDto>>builder()
                    .code(-1)
                    .message("chat while error getting all")
                    .build();
        }

    }
}
