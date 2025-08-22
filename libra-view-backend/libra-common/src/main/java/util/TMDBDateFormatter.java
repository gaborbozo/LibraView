package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public class TMDBDateFormatter {

    public static Instant formatIsoLocalDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    }

}
