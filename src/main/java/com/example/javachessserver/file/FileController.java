package com.example.javachessserver.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> serveFile(@PathVariable("fileId") String fileId) {
        Resource file = storageService.loadAsResource(fileId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping
    public void uploadImage(@RequestBody MultipartFile file) {
        storageService.store(file);
    }
}
