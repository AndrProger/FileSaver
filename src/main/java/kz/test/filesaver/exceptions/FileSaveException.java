package kz.test.filesaver.exceptions;

/**
 * This class represents an exception thrown when there is an error while saving a file. It extends
 * the RuntimeException class, meaning it's an unchecked exception.
 */
public class FileSaveException extends RuntimeException {

  /**
   * Constructs a new FileSaveException with the specified detail message.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *     Throwable.getMessage() method.
   */
  public FileSaveException(String message) {
    super(message);
  }
}
