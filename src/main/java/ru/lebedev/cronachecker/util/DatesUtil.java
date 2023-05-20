package ru.lebedev.cronachecker.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class DatesUtil {
    public static long daysBetweenTwoDates(String startDate, String endDate) {
        var start = parseDateFromString(startDate);
        var end = parseDateFromString(endDate);
        return ChronoUnit.DAYS.between(start, end);
    }

    public static String incrementDay(String date) {
        var currentDate = parseDateFromString(date);
        var nextDate = currentDate.plusDays(1);
        return nextDate.format(DateFormat.FORMATTER);
    }

    private static LocalDate parseDateFromString(String endDate) {
        return LocalDate.parse(endDate, DateFormat.FORMATTER);
    }
}
