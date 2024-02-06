package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.contact.ContactDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import com.example.javaprojectspring_boot.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("groups")
public class GroupController implements SimpleCrud<Integer, GroupDto> {
    private final GroupService groupService;

    @Override
    @PostMapping("/create")
    public ResponseDto<GroupDto> create(@RequestBody @Valid GroupDto dto) {
        return this.groupService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<GroupDto> get(@RequestParam(value = "id") Integer id) {
        return this.groupService.get(id);
    }

    @GetMapping("/getGroupChats")
    public ResponseDto<GroupDto> getGroupChats(@RequestParam(value = "id") Integer id) {
        return this.groupService.getGroupChats(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<GroupDto> update(@Valid @RequestBody GroupDto dto, @RequestParam(value = "id") Integer id) {
        return this.groupService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<GroupDto> delete(@RequestParam(value = "id") Integer id) {
        return this.groupService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<GroupDto>> getAll() {
        return this.groupService.getAll();
    }

    @GetMapping("/getAllWithUSers")
    public ResponseDto<List<UserDto>> getAllWithUsers(@RequestParam(value = "id") Integer id) {
        return this.groupService.getAllWithUsers(id);
    }
}

