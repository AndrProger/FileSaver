package kz.test.filesaver.utils.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import kz.test.filesaver.exceptions.ParseException;

public class FileNameParser {
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
