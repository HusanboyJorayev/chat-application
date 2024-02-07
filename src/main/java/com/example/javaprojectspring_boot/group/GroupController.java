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

    @Override
    public ResponseDto<GroupDto> update(GroupDto dto, Integer id) {
        return null;
    }

    @GetMapping("/getGroupChats")
    public ResponseDto<GroupDto> getGroupChats(@RequestParam(value = "id") Integer id) {
        return this.groupService.getGroupChats(id);
    }

    @PutMapping("/updateGroup")
    public ResponseDto<GroupDto> updateGroup(@Valid @RequestBody GroupDto dto,
                                             @RequestParam(value = "id") Integer id,
                                             @RequestParam(value = "id") Integer userId) {
        return this.groupService.updateGroup(dto, id, userId);
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

    @GetMapping("/getAllWithUsers")
    public ResponseDto<List<UserDto>> getAllWithUsers(@RequestParam(value = "id") Integer id) {
        return this.groupService.getAllWithUsers(id);
    }

    @GetMapping("/addUserToGroup")
    public String addGroup(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "groupId") Integer groupId) {
        return this.groupService.addUserToGroup(userId, groupId);
    }

    @GetMapping("/getUserWithAddedGroup")
    public ResponseDto<List<GroupDto>> getWithUserAddedGroup(@RequestParam(value = "id") Integer id) {
        return this.groupService.getWithUserAddedGroup(id);
    }
}

