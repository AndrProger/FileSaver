package kz.test.filesaver.exceptions;

/**
 * This class represents an exception thrown when there is an I/O error while handling a file. It
 * extends the RuntimeException class, meaning it's an unchecked exception.
 */
public class FileIOException extends RuntimeException {

  /**
   * Constructs a new FileIOException with the specified detail message.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *     Throwable.getMessage() method.
   */
  public FileIOException(String message) {
    super(message);
  }
}
