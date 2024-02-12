package kz.test.filesaver.services;

import static kz.test.filesaver.utils.LoggingUtility.error;
import static kz.test.filesaver.utils.LoggingUtility.info;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import kz.test.filesaver.exceptions.FileSaveException;
import kz.test.filesaver.facade.FileFacade;
import kz.test.filesaver.model.dtos.CommandRequestDTO;
import kz.test.filesaver.model.entities.FileEntity;
import kz.test.filesaver.utils.filecommond.FileCommand;
import kz.test.filesaver.utils.parsers.FileNameParser;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  private final FileFacade fileFacade;

  private final List<FileCommand<?>> fileCommands;

  @Value("${file.storage-path}")
  private String storagePath = "src/main/resources/files";

  public FileService(FileFacade fileFacade, List<FileCommand<?>> fileCommands) {
    this.fileFacade = fileFacade;
    this.fileCommands = fileCommands;
  }

  public String storeFile(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    LocalDateTime fileDate = FileNameParser.parseDateFromFileName(fileName);
    fileFacade.validateFileName(fileName);

    Path filePath = prepareDirectoryAndGetFilePath(fileName);

    try {
      Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      error("Error saving file: " + fileName + " " + e.getMessage());
      throw new FileSaveException("Error saving file: " + fileName);
    }

    FileEntity fileEntity = new FileEntity();
    fileEntity.setFileName(fileName);
    fileEntity.setFileDate(fileDate);
    fileEntity.setFilePath(filePath.toString());
    fileFacade.save(fileEntity);

    info("File uploaded successfully: " + fileName);
    return "File uploaded successfully: " + fileName;
  }

  private Path prepareDirectoryAndGetFilePath(String fileName) {
    Path directoryPath = Paths.get(storagePath);
    try {
      if (!Files.exists(directoryPath)) {
        Files.createDirectories(directoryPath);
        info("Directory created: " + directoryPath);
      }
    } catch (IOException e) {
      error("Error creating directory: " + directoryPath + " message: " + e);
      throw new FileSaveException("Error creating directory: " + directoryPath);
    }
    return directoryPath.resolve(fileName);
  }

  public Object executeCommand(CommandRequestDTO commandRequestDTO) {
    for (val fileCommand : fileCommands) {
      if (fileCommand.checkCommand(commandRequestDTO.getCommand())) {
        info("Executing command: " + commandRequestDTO.getCommand());
        return fileCommand.execute(commandRequestDTO.getFileName());
      }
    }
    error("Command not supported: " + commandRequestDTO.getCommand());
    throw new UnsupportedOperationException(
        "Command not supported: " + commandRequestDTO.getCommand());
  }
}
