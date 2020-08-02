package com.example.javachessserver.file;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FilesController {

    @PostMapping
    @ResponseStatus
    public void uploadImage(@RequestBody MultipartFile file) {

    }
}
