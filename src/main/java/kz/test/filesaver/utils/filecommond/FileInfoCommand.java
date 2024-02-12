package kz.test.filesaver.utils.filecommond;

import kz.test.filesaver.facade.FileFacade;
import kz.test.filesaver.model.entities.FileEntity;
import org.springframework.stereotype.Component;

/**
 * This class implements the FileCommand interface for the "fileInfo" command. It uses the
 * FileFacade to fetch file information based on the filename.
 */
@Component
public class FileInfoCommand implements FileCommand<FileEntity> {
  /** The FileFacade used to fetch file information. */
  private final FileFacade fileFacade;

  /**
   * Constructor for FileInfoCommand.
   *
   * @param fileFacade The FileFacade used to fetch file information.
   */
  public FileInfoCommand(FileFacade fileFacade) {
    this.fileFacade = fileFacade;
  }

  /**
   * Executes the "fileInfo" command. It fetches file information based on the filename.
   *
   * @param filename The name of the file.
   * @return The FileEntity containing the file information.
   */
  @Override
  public FileEntity execute(String filename) {
    return fileFacade.findByFileName(filename);
  }

  /**
   * Checks if the given command is "fileInfo".
   *
   * @param command The command to check.
   * @return true if the command is "fileInfo", false otherwise.
   */
  @Override
  public Boolean checkCommand(String command) {
    return command.equals("fileInfo");
  }
}
