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
    public ResponseDto<ContactDto> get(@RequestParam Long id) {
        return this.contactService.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<ContactDto> update(@Valid @RequestBody ContactDto dto, @RequestParam Long id) {
        return this.contactService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<ContactDto> delete(@RequestParam Long id) {
        return this.contactService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<ContactDto>> getAll() {
        return this.contactService.getAll();
    }
}
