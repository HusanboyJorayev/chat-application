package com.example.javaprojectspring_boot.chat;

import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseDto<ChatDto> get(@RequestParam Integer id) {
        return this.chatService.get(id);
    }

    @GetMapping("/getSenderId")
    public ResponseDto<ChatDto> getSenderId(@RequestParam Integer id) {
        return this.chatService.getSenderId(id);
    }

    @GetMapping("/getReceivedId")
    public ResponseDto<ChatDto> getReceivedId(@RequestParam Integer id) {
        return this.chatService.getReceivedId(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<ChatDto> update(@Valid @RequestBody ChatDto dto, @RequestParam Integer id) {
        return this.chatService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<ChatDto> delete(@RequestParam Integer id) {
        return this.chatService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<ChatDto>> getAll() {
        return this.chatService.getAll();
    }
}
