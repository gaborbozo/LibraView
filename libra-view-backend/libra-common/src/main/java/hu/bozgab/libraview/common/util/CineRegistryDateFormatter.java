package hu.bozgab.libraview.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public class CineRegistryDateFormatter {

    public static Instant formatIsoLocalDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    }

}
