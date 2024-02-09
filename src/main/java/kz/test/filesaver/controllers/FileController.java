package kz.test.filesaver.controllers;

import kz.test.filesaver.model.dtos.CommandRequestDTO;
import kz.test.filesaver.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.storeFile(file);
    }


    @PostMapping("/execute-command")
    public ResponseEntity<Object> executeCommand(@RequestBody CommandRequestDTO commandRequestDTO) {
        try {
            Object result = fileService.executeCommand(commandRequestDTO);
            return ResponseEntity.ok(result);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}