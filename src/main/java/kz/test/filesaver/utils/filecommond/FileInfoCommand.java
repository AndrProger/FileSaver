package kz.test.filesaver.utils.filecommond;

import kz.test.filesaver.facade.FileFacade;
import kz.test.filesaver.model.entities.FileEntity;
import org.springframework.stereotype.Component;


@Component
public class FileInfoCommand implements FileCommand<FileEntity> {
    private final FileFacade fileFacade;

    public FileInfoCommand(FileFacade fileFacade) {
        this.fileFacade = fileFacade;
    }

    @Override
    public FileEntity execute(String filename) {
       return fileFacade.findByFileName(filename);
    }

    @Override
    public Boolean checkCommand(String command) {
        return command.equals("fileInfo");
    }
}

