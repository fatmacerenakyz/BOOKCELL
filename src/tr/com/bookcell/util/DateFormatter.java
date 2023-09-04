package tr.com.bookcell.util;

import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("d-M-yyyy");
    }
}
