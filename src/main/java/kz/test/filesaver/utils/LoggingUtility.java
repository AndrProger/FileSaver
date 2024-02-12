package kz.test.filesaver.utils;

import java.time.LocalDateTime;
import kz.test.filesaver.model.entities.LogEntry;
import kz.test.filesaver.repositories.LogEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingUtility {

  private static LoggingUtility instance;
  private final LogEntryRepository logEntryRepository;

  public LoggingUtility(LogEntryRepository logEntryRepository) {
    this.logEntryRepository = logEntryRepository;
    instance = this;
  }

  public static void info(String message) {
    log.info(message);
    log("INFO", message);
  }

  public static void debug(String message) {
    log.debug(message);
    log("DEBUG", message);
  }

  public static void error(String message) {
    log.error(message);
    log("ERROR", message);
  }

  private static void log(String level, String message) {
    if (instance == null) {
      throw new IllegalStateException("Logging Utility is not initialized");
    }
    instance.logEntry(level, message);
  }

  private void logEntry(String level, String message) {
    LogEntry logEntry = new LogEntry();
    logEntry.setTimestamp(LocalDateTime.now());
    logEntry.setLevel(level);
    logEntry.setMessage(message);
    logEntryRepository.save(logEntry);
  }
}
