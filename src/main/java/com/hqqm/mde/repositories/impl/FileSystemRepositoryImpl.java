package com.hqqm.mde.repositories.impl;

import com.hqqm.mde.config.FileUploadProperties;
import com.hqqm.mde.exceptions.FileNotFoundException;
import com.hqqm.mde.exceptions.FileStorageException;
import com.hqqm.mde.repositories.FileSystemRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Repository
public class FileSystemRepositoryImpl implements FileSystemRepository {

    private final Path filesLocation;
    private final Path imagesLocation;

    public FileSystemRepositoryImpl(FileUploadProperties fileUploadProperties) {
        filesLocation = Paths.get(fileUploadProperties.getFilesLocation())
                .toAbsolutePath()
                .normalize();

        imagesLocation = Paths.get(fileUploadProperties.getImagesLocation())
                .toAbsolutePath()
                .normalize();
        try {
            Files.createDirectories(filesLocation);
            Files.createDirectories(imagesLocation);
        }
        catch (Exception ex) {
            throw new FileStorageException("Could not create upload dir!");
        }
    }

    @Override
    public void saveFiles(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            if (filename == null)
                throw new NullPointerException("File name is null");

            Path dFile = this.filesLocation.resolve(filename);
            try {
                Files.copy(file.getInputStream(), dFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new FileStorageException("Could not save file: " + filename);
            }
        }
    }

    @Override
    public Path saveImage(MultipartFile image) {
        String imageName = image.getOriginalFilename();
        if (imageName == null)
            throw new IllegalArgumentException("Image name is not correct: " + image);

        Path pathToEngineImage = this.imagesLocation.resolve(imageName);
        try {
            Files.copy(image.getInputStream(), pathToEngineImage, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not save engine image: " + imageName);
        }
        return pathToEngineImage;
    }

    @Override
    public void deleteEngineImage(Path pathToImage) {
        try {
            Files.delete(pathToImage);
        } catch (IOException e) {
            throw new FileStorageException(e.toString());
        }
    }

    @Override
    public void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new FileStorageException(e.toString());
        }
    }

    @Override
    public Resource getFile(String fileName) {
        try {
            Path file = this.filesLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists())
                return resource;
            else
                throw new FileNotFoundException("Could not find file: " + fileName);
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not download file: " + fileName);
        }
    }

    @Override
    public byte[] getImage(Path pathToImage) {
        try {
            return Files.readAllBytes(pathToImage);
        } catch (IOException e) {
            throw new FileStorageException("Image not found");
        }
    }

    @Override
    public Path getImagesLocation() {
        return imagesLocation;
    }

    @Override
    public Path getFilesLocation() {
        return filesLocation;
    }
}
