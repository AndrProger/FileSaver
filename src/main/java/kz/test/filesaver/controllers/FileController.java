package kz.test.filesaver.controllers;


import kz.test.filesaver.model.dtos.CommandRequestDTO;
import kz.test.filesaver.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

  private final FileService fileService;

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file) {
    return fileService.storeFile(file);
  }

  @PostMapping("/execute-command")
  public ResponseEntity<Object> executeCommand(@RequestBody CommandRequestDTO commandRequestDTO) {
    Object result = fileService.executeCommand(commandRequestDTO);
    return ResponseEntity.ok(result);
  }
}
