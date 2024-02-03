package com.example.javaprojectspring_boot.chat;

import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatController implements SimpleCrud<Integer, ChatDto> {
    private final ChatService chatService;

    @Override
    @PostMapping("/create")
    public ResponseDto<ChatDto> create(@RequestBody @Valid ChatDto dto) {
        return this.chatService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<ChatDto> get(@RequestParam(value = "id") Integer id) {
        return this.chatService.get(id);
    }


    @Override
    @PutMapping("/update")
    public ResponseDto<ChatDto> update(@Valid @RequestBody ChatDto dto, @RequestParam(value = "id") Integer id) {
        return this.chatService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<ChatDto> delete(@RequestParam(value = "id") Integer id) {
        return this.chatService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<ChatDto>> getAll() {
        return this.chatService.getAll();
    }

    @GetMapping("/SendPhoneGetPhone")
    public ResponseDto<List<ChatDto>> getAllGetterIdAndSenderId(@RequestParam(value = "g") String g, @RequestParam(value = "s") String s) {
        return this.chatService.getAllGetPhoneAndSendPhone(g, s);
    }
}
