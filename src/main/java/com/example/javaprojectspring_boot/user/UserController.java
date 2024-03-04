package com.example.javaprojectspring_boot.user;

import com.example.javaprojectspring_boot.group.GroupDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity<UserDto> get(@RequestParam(value = "id") Integer id) {
        return this.userService.get(id);
    }

    @GetMapping("/getContact")
    public ResponseEntity<UserDto> getContact(@RequestParam(value = "id") Integer id) {
        return this.userService.getContact(id);
    }

    @GetMapping("/getGroup")
    public ResponseEntity<UserDto> getGroup(@RequestParam(value = "id") Integer id) {
        return this.userService.getGroup(id);
    }

    @GetMapping("/getChat")
    public ResponseEntity<UserDto> getChat(@RequestParam(value = "id") Integer id) {
        return this.userService.getChat(id);
    }

    @GetMapping("/getContactAndGroupAndChats")
    public ResponseEntity<UserDto> getContactAndGroupAndChats(@RequestParam(value = "id") Integer id) {
        return this.userService.getContactAndGroupAndChats(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam(value = "id") Integer id, @RequestBody UserDto dto) {
        return this.userService.update(id, dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam(value = "phone") String phone) {
        return this.userService.delete(phone);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/getAddGroupsByContactId")
    public ResponseEntity<List<GroupDto>> getAddGroupsByContactId(@RequestParam(value = "id") Integer id) {
        return this.userService.getAddGroupsByUserId(id);
    }

    @GetMapping("/getAllUsersByChattingOneUser")
    public ResponseEntity<Set<UserDto>> getAllUsersByChattingOneUser(@RequestParam(value = "p") String phone) {
        return this.userService.getAllUsersByChattingOneUser(phone);
    }
}
