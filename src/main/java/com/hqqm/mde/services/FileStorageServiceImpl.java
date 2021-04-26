package com.hqqm.mde.services;

import com.hqqm.mde.config.FileUploadProperties;
import com.hqqm.mde.exceptions.FileNotFoundException;
import com.hqqm.mde.exceptions.FileStorageException;
import com.hqqm.mde.lib.FromRequestParamsMappers.MultipartFileToFileMapper;
import com.hqqm.mde.models.FileEntity;
import com.hqqm.mde.repositories.EngineRepository;
import com.hqqm.mde.repositories.FileRepository;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Getter
    private final Path dirLocation;
    @Getter
    private final Path tmpLocation;
    @Getter
    private final Path imagesLocation;
    private final FileRepository fileRepository;
    private final EngineRepository engineRepository;

    public FileStorageServiceImpl(FileUploadProperties fileUploadProperties, FileRepository fileRepository,
                                  EngineRepository engineRepository)
    {
        this.fileRepository = fileRepository;
        this.engineRepository = engineRepository;
        dirLocation = Paths.get(fileUploadProperties.getLocation())
                .toAbsolutePath()
                .normalize();

        tmpLocation = Paths.get(fileUploadProperties.getTmpLocation())
                .toAbsolutePath()
                .normalize();

        imagesLocation = Paths.get(fileUploadProperties.getImagesLocation())
                .toAbsolutePath()
                .normalize();
        try {
            Files.createDirectories(this.dirLocation);
        }
        catch (Exception ex) {
            throw new FileStorageException("Could not create upload dir!");
        }
    }

    public void saveFilesMetadataInDB(List<MultipartFile> files, Long engineId) {
        List<FileEntity> f = files.stream()
                .map(file -> MultipartFileToFileMapper.mapper(file, engineId, this.dirLocation))
                .collect(Collectors.toList());

        fileRepository.saveFiles(f);
    }

    public void saveFilesInFileSystem(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            if (filename == null)
                throw new NullPointerException("File name is null");

            Path dFile = this.dirLocation.resolve(filename);
            try {
                Files.copy(file.getInputStream(), dFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new FileStorageException("Could not save file: " + filename);
            }

        }
    }

    public void updateFilesInFileSystem(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            if (filename == null)
                throw new NullPointerException("File name is null");

            Path dFile = this.tmpLocation.resolve(filename);
            try {
                Files.copy(file.getInputStream(), dFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new FileStorageException("Could not insert file: " + filename + " in tmp folder");
            }
        }
    }

    public Path saveEngineImageInFileSystem(MultipartFile image) {
        String imageName = image.getOriginalFilename();
        if (imageName == null)
            throw new NullPointerException("Image name is null");

        Path pathToEngineImage = this.imagesLocation.resolve(imageName);
        try {
            Files.copy(image.getInputStream(), pathToEngineImage, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not save engine image: " + imageName);
        }
        return pathToEngineImage;
    }

    public Path updateEngineImageInFileSystem(MultipartFile image) {
        String imageName = image.getOriginalFilename();
        if (imageName == null)
            throw new NullPointerException("Image name is null");

        Path pathToEngineImage = this.tmpLocation.resolve(imageName);
        try {
            Files.copy(image.getInputStream(), pathToEngineImage, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not insert file: " + imageName + " in tmp folder");
        }
        return pathToEngineImage;
    }

    public void deleteEngineFiles(Long engineId) {
        fileRepository.deleteEngineFiles((engineId));
    }

    public Resource loadFile(String fileName) {
        try {
            Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists())
                return resource;
            else
                throw new FileNotFoundException("Could not find file: " + fileName);
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not download file: " + fileName);
        }
    }

    public byte[] getEngineImage(Long engineId) {
        String pathToImageStr = engineRepository.findImagePath(engineId);
        Path pathToImage = Paths.get(pathToImageStr);
        try {
            return Files.readAllBytes(pathToImage);
        } catch (IOException e) {
            throw new FileStorageException("Image not found");
        }
    }
}