package kz.test.filesaver.utils;

import java.time.LocalDateTime;
import kz.test.filesaver.model.entities.LogEntry;
import kz.test.filesaver.repositories.LogEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * This class provides utility methods for logging. It logs messages at different levels (INFO,
 * DEBUG, ERROR) and saves them in a repository.
 */
@Component
@Slf4j
public class LoggingUtility {

  /** The singleton instance of LoggingUtility. */
  private static LoggingUtility instance;

  /** The repository where log entries are saved. */
  private final LogEntryRepository logEntryRepository;

  /**
   * Constructor for LoggingUtility.
   *
   * @param logEntryRepository The repository where log entries are saved.
   */
  public LoggingUtility(LogEntryRepository logEntryRepository) {
    this.logEntryRepository = logEntryRepository;
    instance = this;
  }

  /**
   * Logs an INFO level message.
   *
   * @param message The message to log.
   */
  public static void info(String message) {
    log.info(message);
    log("INFO", message);
  }

  /**
   * Logs a DEBUG level message.
   *
   * @param message The message to log.
   */
  public static void debug(String message) {
    log.debug(message);
    log("DEBUG", message);
  }

  /**
   * Logs an ERROR level message.
   *
   * @param message The message to log.
   */
  public static void error(String message) {
    log.error(message);
    log("ERROR", message);
  }

  /**
   * Logs a message at the given level and saves it in the repository.
   *
   * @param level The level at which to log the message.
   * @param message The message to log.
   */
  private static void log(String level, String message) {
    if (instance == null) {
      throw new IllegalStateException("Logging Utility is not initialized");
    }
    instance.logEntry(level, message);
  }

  /**
   * Creates a new LogEntry with the given level and message, and saves it in the repository.
   *
   * @param level The level of the log entry.
   * @param message The message of the log entry.
   */
  private void logEntry(String level, String message) {
    LogEntry logEntry = new LogEntry();
    logEntry.setTimestamp(LocalDateTime.now());
    logEntry.setLevel(level);
    logEntry.setMessage(message);
    logEntryRepository.save(logEntry);
  }
}
