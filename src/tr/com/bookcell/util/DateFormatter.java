package tr.com.bookcell.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    public static LocalDate dateFormatter(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
