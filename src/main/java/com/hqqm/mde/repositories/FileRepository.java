package com.hqqm.mde.repositories;

import com.hqqm.mde.models.FileEntity;

import java.util.List;

public interface FileRepository {
    List<String> getFileNames(Long engineId);
    void saveFiles(List<FileEntity> files);
    void deleteEngineFiles(Long engineId);
}
