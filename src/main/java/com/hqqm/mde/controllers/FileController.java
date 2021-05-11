package com.hqqm.mde.controllers;

import com.hqqm.mde.services.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FileController {
    FileStorageService fileStorageService;

    @GetMapping("/filenames/{engineId}")
    public List<String> getFileNames(@PathVariable Long engineId) {
        return fileStorageService.getFileNames(engineId);
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource resource = fileStorageService.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/images/{engineId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getEngineImage(@PathVariable Long engineId) {
        return fileStorageService.getEngineImage(engineId);
    }
}