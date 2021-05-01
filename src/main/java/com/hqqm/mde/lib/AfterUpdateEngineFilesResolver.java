package com.hqqm.mde.lib;

import com.hqqm.mde.exceptions.FileStorageException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.support.TransactionSynchronization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@AllArgsConstructor
public class AfterUpdateEngineFilesResolver implements TransactionSynchronization {
    private final Path tmpDir;
    private final Path mainDir;
    private final List<String> filenames;

    @Override
    public void afterCompletion(int status) {
        if (status != STATUS_COMMITTED) {
            for (String filename : filenames) {
                Path file = tmpDir.resolve(filename);
                try {
                    Files.deleteIfExists(file);
                } catch (IOException e) {
                    throw new FileStorageException("Could not delete file " + filename);
                }
            }
        } else {
            for (String filename : filenames) {
                Path tmp = tmpDir.resolve(filename);
                Path main = mainDir.resolve(filename);
                try {
                    Files.move(tmp, main,  REPLACE_EXISTING, ATOMIC_MOVE);
                } catch (IOException e) {
                    throw new FileStorageException("Could not move " + filename + "to main directory");
                }
            }
        }
    }
}
