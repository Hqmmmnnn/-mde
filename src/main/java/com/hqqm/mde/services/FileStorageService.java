package com.hqqm.mde.services;

import com.hqqm.mde.models.EngineFileNames;
import com.hqqm.mde.models.ExportEngineData;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {
    List<EngineFileNames> getFileNames(Long engineId);
    Resource getFile(String fileName);
    List<EngineFileNames> saveFiles(List<MultipartFile> files, Long engineId);
    void deleteFiles(Long engineId);
    void deleteFile(Long fileId);

    byte[] getEngineImage(Long engineId);
    Path saveEngineImageInFS(MultipartFile image);
    void saveImage(MultipartFile file, Long engineId);
    void deleteEngineImage(Long engineId);
    void deleteEngineImageInFS(String path);

    ExportEngineData exportEngineInCSV(Long id);
}
