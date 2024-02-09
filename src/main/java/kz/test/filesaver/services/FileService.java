package kz.test.filesaver.services;

import kz.test.filesaver.facade.FileFacade;
import kz.test.filesaver.model.dtos.CommandRequestDTO;
import kz.test.filesaver.model.entities.FileEntity;
import kz.test.filesaver.utils.filecommond.FileCommand;
import kz.test.filesaver.utils.parsers.FileNameParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileService {

    private final FileFacade fileFacade;

    private final List<FileCommand<?>> fileCommands;

    public FileService(FileFacade fileFacade, List<FileCommand<?>> fileCommands) {
        this.fileFacade = fileFacade;
        this.fileCommands = fileCommands;
    }

    @Value("${file.storage-path}")
    private String storagePath;

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        LocalDateTime fileDate = FileNameParser.parseDateFromFileName(fileName);
        Path filePath = Paths.get(storagePath, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        FileEntity fileEntity = new FileEntity();

        fileEntity.setFileName(fileName);
        fileEntity.setFileDate(fileDate);
        fileEntity.setFilePath(filePath.toString());
        fileFacade.save(fileEntity);

        return "File uploaded successfully: " + fileName;
    }

    public Object  executeCommand(CommandRequestDTO commandRequestDTO) throws IOException {
        for (FileCommand fileCommand : fileCommands) {
            if (fileCommand.checkCommand(commandRequestDTO.getCommand())) {
                return fileCommand.execute(commandRequestDTO.getFileName());
            }
        }
        throw new UnsupportedOperationException("Command not supported: " + commandRequestDTO.getCommand());
    }
}
