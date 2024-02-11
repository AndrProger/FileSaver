package kz.test.filesaver.facade;

import kz.test.filesaver.repositories.LogEntryRepository;
import org.springframework.stereotype.Service;

@Service
public class LogEntryFacade {
    private final LogEntryRepository logEntryRepository;

    public LogEntryFacade(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    public long getTotalLogEntries() {
        return logEntryRepository.count();
    }
}
