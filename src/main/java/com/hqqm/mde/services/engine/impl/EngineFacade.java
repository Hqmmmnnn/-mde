package com.hqqm.mde.services.engine.impl;

import com.hqqm.mde.lib.AfterInsertEngineFilesResolver;
import com.hqqm.mde.lib.AfterInsertEngineImageResolver;
import com.hqqm.mde.lib.FromRequestParamsMappers.FromSaveEngineReqToEngineMapper;
import com.hqqm.mde.models.Engine;
import com.hqqm.mde.models.SaveEngineRequestData;
import com.hqqm.mde.repositories.FileSystemRepository;
import com.hqqm.mde.services.FileStorageService;
import com.hqqm.mde.services.engine.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EngineFacade {
    private final EngineService engineService;
    private final FileStorageService fileStorageService;
    private final FileSystemRepository fileSystemRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long saveEngine(SaveEngineRequestData saveEngineRequestData) {
        Engine engine = FromSaveEngineReqToEngineMapper.map(saveEngineRequestData);
        MultipartFile image = saveEngineRequestData.getImage();
        if (image != null && !image.isEmpty()) {
            Path pathToEngineImage = fileStorageService.saveEngineImageInFS(image);
            var imageResolver = new AfterInsertEngineImageResolver(pathToEngineImage);
            TransactionSynchronizationManager.registerSynchronization(imageResolver);
            engine.setPathToImage(pathToEngineImage.toString());
        }
        Long engineId = engineService.saveEngine(engine);

        List<MultipartFile> files = saveEngineRequestData.getFiles();
        if (files != null && files.stream().allMatch(file -> file.getSize() > 0)) {
            Path mainDir = fileSystemRepository.getFilesLocation();
            List<Path> filePaths = files.stream()
                    .map(file -> mainDir.resolve(file.getOriginalFilename()))
                    .collect(Collectors.toList());

            var filesResolver = new AfterInsertEngineFilesResolver(filePaths);
            TransactionSynchronizationManager.registerSynchronization(filesResolver);
            fileStorageService.saveFiles(files, engineId);
        }

        return engineId;
    }
}
