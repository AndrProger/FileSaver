package kz.test.filesaver.utils.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import kz.test.filesaver.exceptions.ParseException;

/**
 * This class provides a utility method for parsing a date from a file name.
 */
public class FileNameParser {

  /**
   * Parses a date from a file name.
   * The file name is expected to start with a date in the format "ddMMyyyy_HHmmss".
   *
   * @param fileName The name of the file.
   * @return The parsed date.
   * @throws ParseException If the file name does not start with a date in the expected format.
   */
  public static LocalDateTime parseDateFromFileName(String fileName) {
    try {
      String datePart = fileName.split("\\.")[0];
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
      return LocalDateTime.parse(datePart, formatter);
    } catch (Exception e) {
      throw new ParseException(
              "File name is not correct. Was expected  [ddMMyyyy_HHmmss], but you name: " + fileName);
    }
  }
}
