package kz.test.filesaver.controllers;

import kz.test.filesaver.model.dtos.CommandRequestDTO;
import kz.test.filesaver.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class is a REST controller for file operations. It provides endpoints for uploading a file
 * and executing a command.
 */
@RestController
@RequestMapping("/file")
public class FileController {

  /** The service that handles file operations. */
  private final FileService fileService;

  /**
   * Constructor for FileController.
   *
   * @param fileService The service that handles file operations.
   */
  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  /**
   * Endpoint for uploading a file. It receives a file as a multipart file and uses the FileService
   * to store it.
   *
   * @param file The file to upload.
   * @return A string indicating the result of the file upload operation.
   */
  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file) {
    return fileService.storeFile(file);
  }

  /**
   * Endpoint for executing a command. It receives a CommandRequestDTO as the request body and uses
   * the FileService to execute the command.
   *
   * @param commandRequestDTO The command to execute.
   * @return A ResponseEntity containing the result of the command execution.
   */
  @PostMapping("/execute-command")
  public ResponseEntity<Object> executeCommand(@RequestBody CommandRequestDTO commandRequestDTO) {
    Object result = fileService.executeCommand(commandRequestDTO);
    return ResponseEntity.ok(result);
  }
}
