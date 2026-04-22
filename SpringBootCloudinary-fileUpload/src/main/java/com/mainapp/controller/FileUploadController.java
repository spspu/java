package com.mainapp.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mainapp.service.CloudinaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            String imageUrl = cloudinaryService.uploadFile(file.getBytes());
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Upload failed");
        }
    }
    
    @GetMapping("/{name}")
    public ResponseEntity<String> getImage(@PathVariable String name) {
        String url = cloudinaryService.getImageUrl(name);
        return ResponseEntity.ok(url);
    }
    
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteImage(@PathVariable String name) {
        try {
            cloudinaryService.deleteImage(name);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Delete failed");
        }
    }
}