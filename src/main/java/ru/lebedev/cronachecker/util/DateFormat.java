package ru.lebedev.cronachecker.util;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateFormat {

    private static final String FORMAT = "dd.MM.yyyy";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

}
