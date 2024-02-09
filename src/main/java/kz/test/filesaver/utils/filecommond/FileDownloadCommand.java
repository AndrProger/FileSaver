package kz.test.filesaver.utils.filecommond;

import kz.test.filesaver.facade.FileFacade;
import kz.test.filesaver.model.entities.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileDownloadCommand implements FileCommand<byte[]> {
    private final FileFacade fileFacade;

    public FileDownloadCommand(FileFacade fileFacade) {
        this.fileFacade = fileFacade;
    }

    @Override
    public byte[] execute(String filename) throws IOException {
        FileEntity fileEntity = fileFacade.findByFileName(filename);
        Path filePath = Paths.get(fileEntity.getFilePath());

        if (Files.exists(filePath) && Files.isReadable(filePath)) {
            return Files.readAllBytes(filePath);
        } else {
            throw new FileNotFoundException("Could not read file: " + filename);
        }
    }

    @Override
    public Boolean checkCommand(String command) {
        return command.equals("fileDownload");
    }
}
