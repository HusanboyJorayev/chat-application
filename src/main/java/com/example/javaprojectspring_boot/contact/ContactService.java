package com.example.javaprojectspring_boot.contact;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import com.example.javaprojectspring_boot.group.Group;
import com.example.javaprojectspring_boot.group.GroupDto;
import com.example.javaprojectspring_boot.group.GroupMapper;
import com.example.javaprojectspring_boot.group.GroupRepository;
import com.example.javaprojectspring_boot.user.User;
import com.example.javaprojectspring_boot.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService implements SimpleCrud<Long, ContactDto> {
    private final ContactValidation contactValidation;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final ContactMapper contactMapper;

    @Override
    public ResponseDto<ContactDto> create(ContactDto dto) {
        List<ErrorDto> errors = this.contactValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ContactDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        try {
            Contact contact = this.contactMapper.toEntity(dto);
            contact.setCreatedAt(LocalDateTime.now());
            this.contactRepository.save(contact);

            return ResponseDto.<ContactDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.contactMapper.toDto(contact))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ContactDto>builder()
                    .code(-1)
                    .message("contact while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<ContactDto> get(Long id) {
        try {
            return this.contactRepository.findByIdAndDeletedAtIsNull(id)
                    .map(contact -> ResponseDto.<ContactDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.contactMapper.toDto(contact))
                            .build())
                    .orElse(ResponseDto.<ContactDto>builder()
                            .code(-1)
                            .message("contact is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ContactDto>builder()
                    .code(-1)
                    .message("contact while getting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<ContactDto> update(ContactDto dto, Long id) {
        return null;
    }

    public ResponseDto<ContactDto> updateContact(ContactDto dto, Long id, Integer userId) {
        List<ErrorDto> errors = this.contactValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ContactDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .error(errors)
                    .build();
        }
        List<User> users = this.userRepository.deleteContact(userId);
        if (users.isEmpty()) {
            return ResponseDto.<ContactDto>builder()
                    .code(-1)
                    .message("You cannot update this contact")
                    .build();
        }
        try {
            return this.contactRepository.findByIdAndDeletedAtIsNull(id)
                    .map(contact -> {
                        contact.setUpdatedAt(LocalDateTime.now());
                        this.contactMapper.update(contact, dto);
                        this.contactRepository.save(contact);

                        return ResponseDto.<ContactDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.contactMapper.toDto(contact))
                                .build();
                    })
                    .orElse(ResponseDto.<ContactDto>builder()
                            .code(-1)
                            .message("contact is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ContactDto>builder()
                    .code(-1)
                    .message("contact while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<ContactDto> delete(Long id) {
        return null;
    }

    public ResponseDto<ContactDto> deleteContact(Integer id, Long contactId) {
        Optional<Contact> optional = this.contactRepository.findByIdAndDeletedAtIsNull(contactId);
        List<User> users = this.userRepository.deleteContact(id);
        if (users.isEmpty()) {
            return ResponseDto.<ContactDto>builder()
                    .code(-1)
                    .message("You cannot delete this contact")
                    .build();
        }
        Contact contact = optional.get();
        contact.setDeletedAt(LocalDateTime.now());
        this.contactRepository.delete(contact);
        return ResponseDto.<ContactDto>builder()
                .success(true)
                .message("Contact deleted successfully")
                .data(this.contactMapper.toDto(contact))
                .build();

    }

    @Override
    public ResponseDto<List<ContactDto>> getAll() {
        try {
            List<Contact> categories = this.contactRepository.getAllContacts();
            if (categories.isEmpty()) {
                return ResponseDto.<List<ContactDto>>builder()
                        .code(-1)
                        .message("contacts are not found")
                        .build();
            }
            return ResponseDto.<List<ContactDto>>builder()
                    .success(true)
                    .message("Ok")
                    .data(categories.stream().map(this.contactMapper::toDto).toList())
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ContactDto>>builder()
                    .code(-1)
                    .message("contacts while getting all")
                    .build();
        }
    }
    public ResponseEntity<List<GroupDto>> getAddGroupsByContactId(Long id) {
        Optional<Contact> optional = this.contactRepository.findByIdAndDeletedAtIsNull(id);
        List<Group> groupList = this.groupRepository.getAllGroups();
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(List.of(GroupDto.builder()
                    .build()));
        }
        var contact = optional.get();
        String s= contact.getAddGroupsIds();
        List<Group> groups = new ArrayList<>();
        for (Group group : groupList) {
            for (int i=0;i<s.length();i++) {
                if (group.getId().toString().equals(String.valueOf(s.charAt(i)))){
                    groups.add(group);
                }
            }
        }
        return ResponseEntity.ok().body(groups.stream().map(this.groupMapper::toDto).toList());
    }
}
