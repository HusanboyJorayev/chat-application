package com.example.javaprojectspring_boot.contact;

import com.example.javaprojectspring_boot.dto.ErrorDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService implements SimpleCrud<Long, ContactDto> {
    private final ContactValidation contactValidation;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public ResponseDto<ContactDto> create(ContactDto dto) {
        List<ErrorDto> errors = this.contactValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ContactDto>builder()
                    .code(-3)
                    .message("Validation error")
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
                    .message("Type while saving error")
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
                            .message("type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ContactDto>builder()
                    .code(-1)
                    .message("Type while getting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<ContactDto> update(ContactDto dto, Long id) {
        List<ErrorDto> errors = this.contactValidation.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ContactDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .build();
        }
        try {
            return this.contactRepository.findByIdAndDeletedAtIsNull(id)
                    .map(contact -> {
                        this.contactRepository.save(contact);
                        this.contactMapper.update(contact, dto);

                        return ResponseDto.<ContactDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.contactMapper.toDto(contact))
                                .build();
                    })
                    .orElse(ResponseDto.<ContactDto>builder()
                            .code(-1)
                            .message("Type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ContactDto>builder()
                    .code(-1)
                    .message("type while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<ContactDto> delete(Long id) {
        try {
            return this.contactRepository.findByIdAndDeletedAtIsNull(id)
                    .map(contact -> {
                        contact.setDeletedAt(LocalDateTime.now());
                        this.contactRepository.save(contact);

                        return ResponseDto.<ContactDto>builder()
                                .success(true)
                                .message("Ok")
                                .data(this.contactMapper.toDto(contact))
                                .build();
                    })
                    .orElse(ResponseDto.<ContactDto>builder()
                            .code(-1)
                            .message("Type is not found")
                            .build());

        } catch (Exception e) {
            return ResponseDto.<ContactDto>builder()
                    .code(-1)
                    .message("Type while deleting error")
                    .build();
        }
    }

    @Override
    public ResponseDto<List<ContactDto>> getAll() {
        try {
            List<Contact> categories = this.contactRepository.getAllContacts();
            if (categories.isEmpty()) {
                return ResponseDto.<List<ContactDto>>builder()
                        .code(-1)
                        .message("Types are not found")
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
                    .message("Types while getting all")
                    .build();
        }
    }
}
