package com.example.javaprojectspring_boot.group;

import com.example.javaprojectspring_boot.contact.ContactDto;
import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
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
    public ResponseDto<GroupDto> get(@RequestParam Integer id) {
        return this.groupService.get(id);
    }

    @GetMapping("/getgroupChats")
    public ResponseDto<GroupDto> getGroupChats(@RequestParam Integer id) {
        return this.groupService.getGroupChats(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<GroupDto> update(@Valid @RequestBody GroupDto dto, @RequestParam Integer id) {
        return this.groupService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<GroupDto> delete(@RequestParam Integer id) {
        return this.groupService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<GroupDto>> getAll() {
        return this.groupService.getAll();
    }

/*    @GetMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestParam Integer userId, @RequestParam Integer groupId) {
        return this.groupService.addUser(userId, groupId);
    }*/
}

