package kz.test.filesaver.exceptions;

/**
 * This class represents an exception thrown when there is a parsing error. It extends the
 * RuntimeException class, meaning it's an unchecked exception.
 */
public class ParseException extends RuntimeException {

  /**
   * Constructs a new ParseException with the specified detail message.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *     Throwable.getMessage() method.
   */
  public ParseException(String message) {
    super(message);
  }
}
