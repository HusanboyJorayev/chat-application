package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.chat.ChatMapper;
import com.example.javaprojectspring_boot.contact.ContactMapper;
import com.example.javaprojectspring_boot.group.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ContactMapper contactMapper;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;


    public ResponseEntity<UserDto> get(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDto(user);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<UserDto> getContact(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDtoWithContact(user);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<UserDto> getGroup(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDtoWithGroup(user);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<UserDto> getChat(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDtoWithChat(user);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<UserDto> getContactAndGroupAndChats(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDtoWithContactAndGroupAndChat(user);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<String> update(Integer id, UserDto dto) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body("User is not found");
        }
        var user = optional.get();

        if (!this.userRepository.findByPhone(dto.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("You cannot update this user because your phone is wrong");
        }

        user.setUpdatedAt(LocalDateTime.now());
        this.userMapper.update(user, dto);
        this.userRepository.save(user);

        return ResponseEntity.ok().body("User updated");
    }

    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> list = this.userRepository.getAllUsers();

        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(list.stream().map(this.userMapper::toDto).toList());
    }

}
