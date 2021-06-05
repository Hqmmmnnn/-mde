package com.hqqm.mde.exceptions;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(Throwable cause) {
        super(cause);
    }
}
