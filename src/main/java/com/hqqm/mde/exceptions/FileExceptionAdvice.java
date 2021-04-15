package com.hqqm.mde.exceptions;

import com.hqqm.mde.models.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class FileExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<Object> handleFileStorageException(FileStorageException e) {
        List<String> details = List.of(e.getMessage());
        ResponseError err = new ResponseError(LocalDateTime.now(), "File Storage Exception", details);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException e) {
        List<String> details = List.of(e.getMessage());
        ResponseError err = new ResponseError(LocalDateTime.now(), "File Not Found", details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException e) {
        List<String> details = List.of(e.getMessage());
        ResponseError err = new ResponseError(LocalDateTime.now(), "File Size Exceeded", details);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(err);
    }
}