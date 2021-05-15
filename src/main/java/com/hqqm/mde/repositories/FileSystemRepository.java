package com.hqqm.mde.repositories;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileSystemRepository {
    Resource getFile(String fileName);
    void saveFiles(List<MultipartFile> files);
    void deleteFile(Path path);

    byte[] getImage(Path pathToImage);
    Path saveImage(MultipartFile image);
    void deleteEngineImage(Path pathToImage);

    Path getImagesLocation();
    Path getFilesLocation();
}
