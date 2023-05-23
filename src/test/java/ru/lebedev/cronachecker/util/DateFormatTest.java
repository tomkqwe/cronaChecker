package ru.lebedev.cronachecker.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DateFormatTest {
    @Test
    void checkDateFormatPattern() {
        var formatter = DateFormat.FORMATTER;
        var localDate = LocalDate.of(2023, 5, 19);
        var actual = localDate.format(formatter);
        var expected = "19.05.2023";
        assertThat(actual).isEqualTo(expected);
    }
}