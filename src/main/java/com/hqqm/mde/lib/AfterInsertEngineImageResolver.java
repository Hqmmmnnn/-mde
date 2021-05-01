package com.hqqm.mde.lib;

import com.hqqm.mde.exceptions.FileStorageException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.support.TransactionSynchronization;

import java.nio.file.Files;
import java.nio.file.Path;

@AllArgsConstructor
public class AfterInsertEngineImageResolver implements TransactionSynchronization {
    private final Path pathToEngineImage;

    @Override
    public void afterCompletion(int status) {
        if (status != STATUS_COMMITTED) {
            try {
                if (Files.exists(pathToEngineImage))
                    Files.delete(pathToEngineImage);
            } catch (Exception e) {
                throw new FileStorageException("AfterInsertEngineImageResolver could not delete the file: " + pathToEngineImage.getFileName());
            }
        }
    }
}
