package com.hqqm.mde.controllers;

import com.hqqm.mde.models.EngineFileNames;
import com.hqqm.mde.models.ExportEngineData;
import com.hqqm.mde.services.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@RestController @RequestMapping("/api")
@AllArgsConstructor
public class FileController {
    FileStorageService fileStorageService;

    @GetMapping("/filenames/{engineId}")
    public List<EngineFileNames> getFileNames(@PathVariable Long engineId) {
        return fileStorageService.getFileNames(engineId);
    }

    @DeleteMapping("/files/{fileId}")
    @PreAuthorize("hasAuthority('engines:delete')")
    public void deleteFile(@PathVariable Long fileId) {
        fileStorageService.deleteFile(fileId);
    }

    @PostMapping("/files/{engineId}")
    @PreAuthorize("hasAuthority('engines:write')")
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

    @GetMapping(value = "/download/csv/{engineId}", produces = "text/csv")
    public ResponseEntity<String> exportEngineInCSV(@PathVariable Long engineId) {
        ExportEngineData data = fileStorageService.exportEngineInCSV(engineId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + data.getModel() + ".csv\"" )
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(data.getData());
    }

    @GetMapping(value = "/images/{engineId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getEngineImage(@PathVariable Long engineId) {
        return fileStorageService.getEngineImage(engineId);
    }

    @DeleteMapping("/images/{engineId}")
    @PreAuthorize("hasAuthority('engines:delete')")
    public void deleteEngineImage(@PathVariable Long engineId) {
        fileStorageService.deleteEngineImage(engineId);
    }

    @PostMapping("/images/{engineId}")
    @PreAuthorize("hasAuthority('engines:write')")
    public void saveEngineImage(@PathVariable Long engineId, @RequestParam MultipartFile image) {
        fileStorageService.saveImage(image, engineId);
    }
}