package com.hqqm.mde.services;

import com.hqqm.mde.lib.FromRequestParamsMappers.MultipartFileToFileMapper;
import com.hqqm.mde.models.EngineFileNames;
import com.hqqm.mde.models.ExportEngineData;
import com.hqqm.mde.models.FileEntity;
import com.hqqm.mde.models.RequestParamsForEngineFiltration;
import com.hqqm.mde.repositories.EngineRepository;
import com.hqqm.mde.repositories.FileRepository;
import com.hqqm.mde.repositories.FileSystemRepository;
import com.hqqm.mde.services.engine.impl.EngineFilter;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final FileRepository fileRepository;
    private final FileSystemRepository fileSystemRepository;
    private final EngineRepository engineRepository;

    public FileStorageServiceImpl(FileRepository fileRepository, EngineRepository engineRepository,
                                  FileSystemRepository fileSystemRepository)
    {
        this.fileRepository = fileRepository;
        this.fileSystemRepository = fileSystemRepository;
        this.engineRepository = engineRepository;
    }

    public List<EngineFileNames> getFileNames(Long engineId) {
        return fileRepository.getFileNames(engineId);
    }

    public List<EngineFileNames> saveFiles(List<MultipartFile> files, Long engineId)  {
        List<FileEntity> f = files.stream()
                .map(file -> MultipartFileToFileMapper.mapper(file, engineId, fileSystemRepository.getFilesLocation()))
                .collect(Collectors.toList());

        fileSystemRepository.saveFiles(files);
        fileRepository.saveFiles(f);
        List<String> filenames = f.stream().map(FileEntity::getName).collect(Collectors.toList());
        return fileRepository.getFileNames(engineId, filenames);
    }

    public Path saveEngineImageInFS(MultipartFile image) {
        return fileSystemRepository.saveImage(image);
    }

    public void deleteFiles(Long engineId) {
        var paths = fileRepository.deleteFiles(engineId);
        paths.stream()
                .map(Path::of)
                .forEach(fileSystemRepository::deleteFile);
    }

    public void deleteFile(Long fileId) {
        var path = fileRepository.deleteFile(fileId);
        fileSystemRepository.deleteFile(Path.of(path));
    }

    public Resource getFile(String fileName) {
        return fileSystemRepository.getFile(fileName);
    }

    public byte[] getEngineImageForUpdate(Long engineId) {
        Optional<String> pathToImageOpt = engineRepository.findImagePath(engineId);
        return fileSystemRepository.getImageForUpdate(pathToImageOpt.map(Path::of));
    }

    public byte[] getEngineImage(Long engineId) {
        Optional<String> pathToImageOpt = engineRepository.findImagePath(engineId);
        return fileSystemRepository.getImage(pathToImageOpt.map(Path::of));
    }

    public void deleteEngineImage(Long engineId) {
        String path = engineRepository.deleteEngineImage(engineId);
        fileSystemRepository.deleteEngineImage(Path.of(path));
    }

    public void deleteEngineImageInFS(String path) {
        fileSystemRepository.deleteFile(Path.of(path));
    }

    public void saveImage(MultipartFile image, Long engineId) {
        fileSystemRepository.saveImage(image);
        String imageName = image.getOriginalFilename();
        if (imageName == null)
            throw new IllegalArgumentException("Image name is not correct");

        String pathToImage = fileSystemRepository.getImagesLocation().resolve(imageName).toString();
        engineRepository.updateEngineImage(pathToImage, engineId);
    }

    public ExportEngineData exportEngineInCSV(Long id) {
        return engineRepository.exportEngineInCSV(id);
    }

    public ExportEngineData exportEnginesInCSVByRequestParams(RequestParamsForEngineFiltration reqParams) {
        var engineFilter = new EngineFilter(reqParams);
        return engineRepository.exportEnginesBy(engineFilter.getCondition());
    }
}
