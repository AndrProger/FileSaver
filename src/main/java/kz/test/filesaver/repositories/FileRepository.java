package kz.test.filesaver.repositories;

import java.util.Optional;
import kz.test.filesaver.model.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
  /**
   * Finds a FileEntity by its file name.
   *
   * @param fileName the name of the file
   * @return an Optional containing the FileEntity if found, or an empty Optional if not found
   */
  Optional<FileEntity> findByFileName(String fileName);

  /**
   * Checks if a FileEntity with the given file name exists.
   *
   * @param fileName the name of the file
   * @return true if a FileEntity with the given file name exists, false otherwise
   */
  Boolean existsByFileName(String fileName);
}
