package com.example.javaprojectspring_boot.file;


import com.example.javaprojectspring_boot.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("audio")
public class AudioController {

    private final AudioService audioService;

    @PostMapping("/upload")
    public ResponseDto<AudioResponse> upload(@RequestBody MultipartFile file) {
        return this.audioService.upload(file);
    }

    @GetMapping("/download")
    public ResponseDto<AudioResponse> download(@RequestParam(value = "id") Integer id) {
        return this.audioService.download(id);
    }

    @PutMapping("/update")
    public ResponseDto<AudioResponse> update(@RequestBody MultipartFile file, @RequestParam(value = "id") Integer id) {
        return this.audioService.update(file, id);
    }

    @DeleteMapping("/delete")
    ResponseDto<AudioResponse> delete(@RequestParam(value = "id") Integer id) {
        return this.audioService.delete(id);
    }
}
