package kz.test.filesaver.repositories;

import java.util.Optional;
import kz.test.filesaver.model.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
  Optional<FileEntity> findByFileName(String fileName);

  Boolean existsByFileName(String fileName);
}
