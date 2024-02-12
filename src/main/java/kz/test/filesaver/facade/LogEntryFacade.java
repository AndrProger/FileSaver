package kz.test.filesaver.facade;

import kz.test.filesaver.repositories.LogEntryRepository;
import org.springframework.stereotype.Service;

/**
 * This class provides a facade for log entry operations. It abstracts the complexity of the
 * repository layer and provides a simple interface for log entry operations. It is annotated
 * with @Service to indicate that it's a service component in the Spring framework.
 */
@Service
public class LogEntryFacade {
  private final LogEntryRepository logEntryRepository;

  /**
   * Constructs a new LogEntryFacade with the specified LogEntryRepository.
   *
   * @param logEntryRepository the repository that this facade will use for log entry operations
   */
  public LogEntryFacade(LogEntryRepository logEntryRepository) {
    this.logEntryRepository = logEntryRepository;
  }

  /**
   * Retrieves the total number of log entries from the repository.
   *
   * @return the total number of log entries
   */
  public long getTotalLogEntries() {
    return logEntryRepository.count();
  }
}
