package kz.test.filesaver.repositories;

import kz.test.filesaver.model.entities.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}