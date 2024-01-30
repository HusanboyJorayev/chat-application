package com.example.javaprojectspring_boot.user;

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
    public ResponseEntity<UserDto> get(@RequestParam Integer id) {
        return this.userService.get(id);
    }

    @GetMapping("/getContact")
    public ResponseEntity<UserDto> getContact(@RequestParam Integer id) {
        return this.userService.getContact(id);
    }

    @GetMapping("/getGroup")
    public ResponseEntity<UserDto> getGroup(@RequestParam Integer id) {
        return this.userService.getGroup(id);
    }

    @GetMapping("/getChat")
    public ResponseEntity<UserDto> getChat(@RequestParam Integer id) {
        return this.userService.getChat(id);
    }

    @GetMapping("/getContactAndGroupAndChats")
    public ResponseEntity<UserDto> getContactAndGroupAndChats(@RequestParam Integer id) {
        return this.userService.getContactAndGroupAndChats(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam Integer id, @RequestBody UserDto dto) {
        return this.userService.update(id, dto);
    }

    @GetMapping("/getAllUsers")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }
}
