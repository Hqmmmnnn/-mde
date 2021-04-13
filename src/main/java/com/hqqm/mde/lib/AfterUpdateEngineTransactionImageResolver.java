package com.hqqm.mde.lib;

import com.hqqm.mde.exceptions.FileStorageException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.support.TransactionSynchronization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@AllArgsConstructor
public class AfterUpdateEngineTransactionImageResolver implements TransactionSynchronization {
    private final Path tmpDir;
    private final Path imagesDir;
    private final String imageName;

    @Override
    public void afterCompletion(int status) {
        if (status != STATUS_COMMITTED) {
            try {
                Path pathToImage = imagesDir.resolve(imageName);
                Files.deleteIfExists(pathToImage);
            } catch (IOException e) {
                throw new FileStorageException("Could not delete file " + imageName);
            }
        } else {
            try {
                Path tmp = tmpDir.resolve(imageName);
                Path pathToImage = imagesDir.resolve(imageName);
                Files.move(tmp, pathToImage,  REPLACE_EXISTING, ATOMIC_MOVE);
            } catch (IOException e) {
                throw new FileStorageException("Could not move " + imageName + "to main directory");
            }
        }
    }
}
