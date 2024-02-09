package kz.test.filesaver.utils.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileNameParser {
    public static LocalDateTime parseDateFromFileName(String fileName) {
        String datePart = fileName.split("\\.")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        return LocalDateTime.parse(datePart, formatter);
    }
}
