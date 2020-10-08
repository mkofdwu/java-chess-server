package com.example.javachessserver;

import com.example.javachessserver.file.FileController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class JavaChessServerApplication {

    public static void main(String[] args) {
        new File(FileController.uploadDir).mkdirs();
        SpringApplication.run(JavaChessServerApplication.class, args);
    }

}
