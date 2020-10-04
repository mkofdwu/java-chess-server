package com.example.javachessserver.file;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FilesController {

    @GetMapping("/{imageId}")
    public void getImage(@PathVariable("imageId") String imageId) {

    }

    @PostMapping
    @ResponseStatus
    public void uploadImage(@RequestBody MultipartFile file) {

    }
}
