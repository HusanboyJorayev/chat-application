package com.example.javaprojectspring_boot.contact;

import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("contact")
public class ContactController implements SimpleCrud<Long, ContactDto> {
    private final ContactService contactService;

    @Override
    @PostMapping("/create")
    public ResponseDto<ContactDto> create(@RequestBody @Valid ContactDto dto) {
        return this.contactService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<ContactDto> get(@RequestParam(value = "id") Long id) {
        return this.contactService.get(id);
    }

    @Override
    public ResponseDto<ContactDto> update(ContactDto dto, Long id) {
        return null;
    }

    @PutMapping("/updateContact")
    public ResponseDto<ContactDto> updateContact(@Valid @RequestBody ContactDto dto,
                                                 @RequestParam(value = "id") Long id,
                                                 @RequestParam(value = "id") Integer userId) {
        return this.contactService.updateContact(dto, id, userId);
    }

    @Override
    public ResponseDto<ContactDto> delete(Long id) {
        return null;
    }

    @DeleteMapping("/deleteContact")
    public ResponseDto<ContactDto> deleteContact(@RequestParam(value = "id") Integer id, @RequestParam(value = "id") Long contactId) {
        return this.contactService.deleteContact(id, contactId);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<ContactDto>> getAll() {
        return this.contactService.getAll();
    }
}
