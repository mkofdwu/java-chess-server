package com.example.javachessserver.file;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {
    public static final String uploadDir = System.getProperty("user.dir") + "/static/uploads";

    @PostMapping
    public UploadedFileUrl uploadImage(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename().replaceAll("\\s", "-"); // slugify
        Path filePath = Paths.get(uploadDir, filename);
        try {
            Files.write(filePath, file.getBytes());
            return new UploadedFileUrl("http://localhost:8081/api/uploads/" + filename);
        } catch (IOException exception) {
            System.out.println("Failed to save uploaded file");
            exception.printStackTrace();
            return null;
        }
    }
}
