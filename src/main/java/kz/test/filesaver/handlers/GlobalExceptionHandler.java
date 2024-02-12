package kz.test.filesaver.handlers;

import static kz.test.filesaver.utils.LoggingUtility.error;

import jakarta.persistence.EntityNotFoundException;
import kz.test.filesaver.exceptions.AlreadyExistException;
import kz.test.filesaver.exceptions.FileIOException;
import kz.test.filesaver.exceptions.FileSaveException;
import kz.test.filesaver.exceptions.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(FileSaveException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<String> handleFileSaveException(FileSaveException ex) {
    error("Error saving file: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Error saving file: " + ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<String> handleAllUncaughtException(Exception ex) {
    error("An unexpected error occurred: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An unexpected error occurred");
  }

  @ExceptionHandler(AlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<String> handleAlreadyExistException(AlreadyExistException ex) {
    error("Conflict: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }

  @ExceptionHandler(FileIOException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<String> handleFileIOException(FileIOException ex) {
    error("File IO error: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("File IO error: " + ex.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
    error("Entity not found: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(ParseException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleParseException(ParseException ex) {
    error("Parsing error: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parsing error: " + ex.getMessage());
  }

  @ExceptionHandler(UnsupportedOperationException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ResponseEntity<String> handleUnsupportedOperationException(
      UnsupportedOperationException ex) {
    error("Unsupported operation: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body("Unsupported operation: " + ex.getMessage());
  }
}
