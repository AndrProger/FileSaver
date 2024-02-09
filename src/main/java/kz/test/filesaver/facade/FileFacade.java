package kz.test.filesaver.facade;

import jakarta.persistence.EntityNotFoundException;
import kz.test.filesaver.model.entities.FileEntity;
import kz.test.filesaver.repositories.FileRepository;
import org.springframework.stereotype.Service;

@Service
public class FileFacade {
    private final FileRepository fileRepository;

    public FileFacade(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity findById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File not found with id: " + id));
    }

    public FileEntity save(FileEntity fileEntity) {
        return fileRepository.save(fileEntity);
    }

    public FileEntity findByFileName(String fileName) {
        return fileRepository.findByFileName(fileName)
                .orElseThrow(() -> new EntityNotFoundException("File not found with name: " + fileName));
    }
}
