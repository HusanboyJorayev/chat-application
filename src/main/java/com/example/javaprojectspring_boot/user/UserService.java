package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.chat.Chat;
import com.example.javaprojectspring_boot.chat.ChatMapper;
import com.example.javaprojectspring_boot.chat.ChatRepository;
import com.example.javaprojectspring_boot.contact.Contact;
import com.example.javaprojectspring_boot.contact.ContactMapper;
import com.example.javaprojectspring_boot.contact.ContactRepository;
import com.example.javaprojectspring_boot.group.Group;
import com.example.javaprojectspring_boot.group.GroupDto;
import com.example.javaprojectspring_boot.group.GroupMapper;
import com.example.javaprojectspring_boot.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.javaprojectspring_boot.user.Role.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;
    private final ChatRepository chatRepository;
    private final GroupRepository groupRepository;


    public ResponseEntity<UserDto> get(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(UserDto.builder()
                            .messages(List.of())
                    .build());
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDto(user);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<UserDto> getContact(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(UserDto.builder().build());
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDtoWithContact(user);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<UserDto> getGroup(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(UserDto.builder().build());
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDtoWithGroup(user);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<UserDto> getChat(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(UserDto.builder().build());
        }
        User user = optional.get();
        UserDto dto = this.userMapper.toDtoWithChat(user);
        return ResponseEntity.ok().body(dto);
    }


    public ResponseEntity<UserDto> getContactAndGroupAndChats(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(UserDto.builder().build());
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

    public ResponseEntity<String> delete(String phone) {
        Optional<User> userList = this.userRepository.findByPhoneNumberAndDeletedAtIsNull(phone);
        if (userList.isEmpty()) {
            return ResponseEntity.badRequest().body("User is not found");
        }
        var user = userList.get();
        user.setDeletedAt(LocalDateTime.now());
        this.userRepository.delete(user);
        return ResponseEntity.ok().body("User delete successfully");
    }

    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> list = this.userRepository.getAllUsers();

        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(List.of(UserDto.builder().build()));
        }
        return ResponseEntity.ok().body(list.stream().map(this.userMapper::toDto).toList());
    }

    public ResponseEntity<List<GroupDto>> getAddGroupsByUserId(Integer id) {
        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        List<Group> groupList = this.groupRepository.getAllGroups();
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(List.of(GroupDto.builder()
                    .build()));
        }
        var user = optional.get();
        List<Group> groups = new ArrayList<>();
        for (Group group : groupList) {
            for (Integer integer : user.getIntegers()) {
                if (group.getId().equals(integer)) {
                    groups.add(group);
                }
            }
        }
        return ResponseEntity.ok().body(groups.stream().map(this.groupMapper::toDto).toList());
    }

    public ResponseEntity<Set<UserDto>> getAllUsersByChattingOneUser(String phone) {
        List<User> userList = this.userRepository.getAllUsers();
        List<Chat> chatList = this.chatRepository.getAllUsersByChattingByOneUser(phone);
        if (chatList.isEmpty()) {
            return ResponseEntity.badRequest().body(Set.of());
        }
        if (userList.isEmpty()) {
            return ResponseEntity.badRequest().body(Set.of());
        }
        Set<User> chattingUsers = new HashSet<>();
        for (User user : userList) {
            for (Chat chat : chatList) {
                if (user.getPhoneNumber().equals(chat.getGetPhone()) ||
                        user.getPhoneNumber().equals(chat.getSendPhone())
                ) {
                    chattingUsers.add(user);
                }
            }
        }
        User user = new User();
        for (User u : chattingUsers) {
            if (u.getPhoneNumber().equals(phone))
                user = u;
            System.out.println(user);
        }
        chattingUsers.remove(user);
        return ResponseEntity.ok().body(chattingUsers.stream().map(this.userMapper::toDto).collect(Collectors.toSet()));
    }
}
