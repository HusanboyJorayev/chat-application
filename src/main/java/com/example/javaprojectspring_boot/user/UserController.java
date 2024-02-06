package com.example.javaprojectspring_boot.user;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getUserWithChats")
    public ResponseEntity<List<UserDto>> getUsersWithChatting(@RequestParam(value = "id") Integer id) {
        return this.userService.getUsersWithChatting(id);
    }

    @GetMapping("/getContactAndGroupAndChats")
    public ResponseEntity<UserDto> getContactAndGroupAndChats(@RequestParam(value = "id") Integer id) {
        return this.userService.getContactAndGroupAndChats(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam(value = "id") Integer id, @RequestBody UserDto dto) {
        return this.userService.update(id, dto);
    }

    @GetMapping("/getAllUsers")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }
}
