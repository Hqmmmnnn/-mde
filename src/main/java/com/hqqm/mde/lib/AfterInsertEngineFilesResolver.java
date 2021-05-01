package com.hqqm.mde.lib;

import com.hqqm.mde.exceptions.FileStorageException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.support.TransactionSynchronization;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
public class AfterInsertEngineFilesResolver implements TransactionSynchronization {
    private final List<Path> filePaths;

    @Override
    public void afterCompletion(int status) {
        if (status != STATUS_COMMITTED) {
            for (Path pathToFile : filePaths) {
                try {
                    if (Files.exists(pathToFile))
                        Files.delete(pathToFile);
                } catch (Exception e) {
                    throw new FileStorageException("Cleanup Transaction Listener didn't delete the file: " + pathToFile.getFileName());
                }
            }
        }
    }
}
