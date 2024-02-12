package kz.test.filesaver.facade;

import jakarta.persistence.EntityNotFoundException;
import kz.test.filesaver.exceptions.AlreadyExistException;
import kz.test.filesaver.model.entities.FileEntity;
import kz.test.filesaver.repositories.FileRepository;
import org.springframework.stereotype.Service;

/**
 * This class provides a facade for file operations. It abstracts the complexity of the repository
 * layer and provides a simple interface for file operations. It is annotated with @Service to
 * indicate that it's a service component in the Spring framework.
 */
@Service
public class FileFacade {
  private final FileRepository fileRepository;

  /**
   * Constructs a new FileFacade with the specified FileRepository.
   *
   * @param fileRepository the repository that this facade will use for file operations
   */
  public FileFacade(FileRepository fileRepository) {
    this.fileRepository = fileRepository;
  }

  /**
   * Saves a FileEntity to the repository.
   *
   * @param fileEntity the file entity to be saved
   */
  public FileEntity save(FileEntity fileEntity) {
    return fileRepository.save(fileEntity);
  }

  /**
   * Finds a FileEntity by its file name.
   *
   * @param fileName the name of the file to be found
   * @return the found FileEntity
   * @throws EntityNotFoundException if no file is found with the specified name
   */
  public FileEntity findByFileName(String fileName) {
    return fileRepository
        .findByFileName(fileName)
        .orElseThrow(() -> new EntityNotFoundException("File not found with name: " + fileName));
  }

  /**
   * Validates a file name.
   *
   * @param fileName the name of the file to be validated
   * @throws AlreadyExistException if a file already exists with the specified name
   */
  public void validateFileName(String fileName) {
    if (fileRepository.existsByFileName(fileName)) {
      throw new AlreadyExistException("File already exist with name: " + fileName);
    }
  }
}
