package kz.test.filesaver.repositories;

import kz.test.filesaver.model.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    public Optional<FileEntity> findByFileName(String fileName);
}
