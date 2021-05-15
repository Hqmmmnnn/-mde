package com.hqqm.mde.controllers;

import com.hqqm.mde.models.EngineFileNames;
import com.hqqm.mde.services.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
public class FileController {
    FileStorageService fileStorageService;

    @GetMapping("/filenames/{engineId}")
    public List<EngineFileNames> getFileNames(@PathVariable Long engineId) {
        return fileStorageService.getFileNames(engineId);
    }

    @DeleteMapping("/filenames/{fileId}")
    public void deleteFile(@PathVariable Long fileId) {
        fileStorageService.deleteFile(fileId);
    }

    @PostMapping("/filenames/{engineId}")
    public List<EngineFileNames> saveEngineFiles(@PathVariable Long engineId, @RequestParam List<MultipartFile> files) {
        return fileStorageService.saveFiles(files, engineId);
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource resource = fileStorageService.getFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/images/{engineId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getEngineImage(@PathVariable Long engineId) {
        return fileStorageService.getEngineImage(engineId);
    }

    @DeleteMapping("/images/{engineId}")
    public void deleteEngineImage(@PathVariable Long engineId) {
        fileStorageService.deleteEngineImage(engineId);
    }

    @PostMapping("/images/{engineId}")
    public void saveEngineImage(@PathVariable Long engineId, @RequestParam MultipartFile image) {
        fileStorageService.saveImage(image, engineId);
    }
}