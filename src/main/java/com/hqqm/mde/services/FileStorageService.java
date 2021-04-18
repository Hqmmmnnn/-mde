package com.hqqm.mde.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileStorageService {
    void saveFilesMetadataInDB(List<MultipartFile> files, Long engineId);
    void saveFilesInFileSystem(List<MultipartFile> files);
    void updateFilesInFileSystem(List<MultipartFile> files);
    Path saveEngineImageInFileSystem(MultipartFile image);
    Path updateEngineImageInFileSystem(MultipartFile image);
    void deleteEngineFiles(Long engineId);
    Resource loadFile(String fileName);
    byte[] getEngineImage(Long engineId);
    Path getTmpLocation();
    Path getImagesLocation();
    Path getDirLocation();
}
