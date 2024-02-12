package kz.test.filesaver.exceptions;

/**
 * This class represents an exception thrown when an entity already exists. It extends the
 * RuntimeException class, meaning it's an unchecked exception.
 */
public class AlreadyExistException extends RuntimeException {

  /**
   * Constructs a new AlreadyExistException with the specified detail message.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *     Throwable.getMessage() method.
   */
  public AlreadyExistException(String message) {
    super(message);
  }
}
