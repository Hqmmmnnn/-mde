package com.hqqm.mde.repositories;

import com.hqqm.mde.models.EngineFileNames;
import com.hqqm.mde.models.FileEntity;

import java.util.List;

public interface FileRepository {
    List<EngineFileNames> getFileNames(Long engineId, List<String> names);
    List<EngineFileNames> getFileNames(Long engineId);
    void saveFiles(List<FileEntity> files);
    List<String> deleteFiles(Long engineId);
    String deleteFile(Long fileId);
}
