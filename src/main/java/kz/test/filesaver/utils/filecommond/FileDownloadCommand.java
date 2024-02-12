package kz.test.filesaver.utils.filecommond;

import static kz.test.filesaver.utils.LoggingUtility.error;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import kz.test.filesaver.exceptions.FileIOException;
import kz.test.filesaver.facade.FileFacade;
import kz.test.filesaver.model.entities.FileEntity;
import org.springframework.stereotype.Component;

/**
 * FileDownloadCommand is a component that implements the FileCommand interface with a return type of byte array.
 * It is responsible for downloading a file given its filename.
 */
@Component
public class FileDownloadCommand implements FileCommand<byte[]> {

  private final FileFacade fileFacade;

  /**
   * Constructor for FileDownloadCommand.
   * @param fileFacade An instance of FileFacade.
   */
  public FileDownloadCommand(FileFacade fileFacade) {
    this.fileFacade = fileFacade;
  }

  /**
   * This method is used to execute the file download command.
   * It first retrieves the FileEntity from the repository using the filename.
   * Then it checks if the file is accessible, if not it throws a FileIOException.
   * If the file is accessible, it reads all bytes from the file and returns them.
   * If an IOException occurs during reading the file, it throws a FileIOException.
   * @param filename The name of the file to be downloaded.
   * @return A byte array containing the file data.
   */
  @Override
  public byte[] execute(String filename) {
    FileEntity fileEntity = fileFacade.findByFileName(filename);
    Path filePath = Paths.get(fileEntity.getFilePath());

    if (!isFileAccessible(filePath)) {
      error("Could not read file: " + filename);
      throw new FileIOException("Could not read file: " + filename);
    }
    try {
      return Files.readAllBytes(filePath);
    } catch (IOException e) {
      error("Error reading file: " + filename+ " " + e.getMessage()+ " " + Arrays.toString(e.getStackTrace()));
      throw new FileIOException("Error reading file: " + filename);
    }
  }

  /**
   * This method checks if a file is accessible by checking if it exists and if it is readable.
   * @param filePath The path of the file to be checked.
   * @return A boolean indicating whether the file is accessible or not.
   */
  private boolean isFileAccessible(Path filePath) {
    return Files.exists(filePath) && Files.isReadable(filePath);
  }

  /**
   * This method checks if the provided command equals "fileDownload".
   * @param command The command to be checked.
   * @return A boolean indicating whether the command equals "fileDownload" or not.
   */
  @Override
  public Boolean checkCommand(String command) {
    return command.equals("fileDownload");
  }
}
