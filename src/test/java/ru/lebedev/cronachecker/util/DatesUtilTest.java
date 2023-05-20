package ru.lebedev.cronachecker.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.lebedev.cronachecker.util.DatesUtil.daysBetweenTwoDates;

class DatesUtilTest {


    @Test
    void checkDifferenceBetweenTwoDates() {
        var actual = daysBetweenTwoDates("18.05.2023", "20.05.2023");
        var expected = 2L;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkIncrementDay() {
        var actual1 = DatesUtil.incrementDay("20.05.2023");
        var actual2 = DatesUtil.incrementDay("30.04.2023");
        var actual3 = DatesUtil.incrementDay("31.12.2022");
        var expected1 = "21.05.2023";
        var expected2 = "01.05.2023";
        var expected3 = "01.01.2023";
        assertThat(actual1).isEqualTo(expected1);

        assertThat(actual2).isEqualTo(expected2);

        assertThat(actual3).isEqualTo(expected3);
    }

}