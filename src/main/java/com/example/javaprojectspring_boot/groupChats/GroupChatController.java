package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.dto.ResponseDto;
import com.example.javaprojectspring_boot.dto.SimpleCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("groupChat")
public class GroupChatController implements SimpleCrud<Integer, GroupChatDto> {
    private final GroupChatService groupChatService;

    @Override
    @PostMapping("/create")
    public ResponseDto<GroupChatDto> create(@RequestBody @Valid GroupChatDto dto) {
        return this.groupChatService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<GroupChatDto> get(@RequestParam Integer id) {
        return this.groupChatService.get(id);
    }

    @GetMapping("/getWithUsers")
    public ResponseDto<GroupChatDto> getWithUsers(Integer id) {
        return this.groupChatService.getWithUsers(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<GroupChatDto> update(@Valid @RequestBody GroupChatDto dto, @RequestParam Integer id) {
        return this.groupChatService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<GroupChatDto> delete(@RequestParam Integer id) {
        return this.groupChatService.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseDto<List<GroupChatDto>> getAll() {
        return this.groupChatService.getAll();
    }
}
