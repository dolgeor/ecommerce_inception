package util.validation;


import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static util.validation.userdefined.DateValidator.isLeapYear;
import static util.validation.userdefined.DateValidator.isValidDate;

public class DateValidExtractorTest {
    @Test
    public void leapYearsShouldBeDivisibleByFour() {
        assertTrue(isLeapYear(2016L));
    }

    @Test
    public void normalYearsShouldNotBeDivisibleByFour() {
        assertFalse(isLeapYear(2015L));
    }

    @Test
    public void leapYearsShouldNotBeDivisibleByOneHundred() {
        assertFalse(isLeapYear(1900L));
    }

    @Test
    public void leapYearsShouldBeDivisibleByFourHundred() {
        assertTrue(isLeapYear(2000L));
    }

    @Test
    public void normalDateShouldBeValid() {
        final Long year = 1996L;
        final Long month = 4L;
        final Long day = 17L;
        assertTrue(isValidDate(year, month, day));
    }

    @Test
    public void dateWithMonthBiggerLastOneShouldNotBeValid() {
        final Long year = 1996L;
        final Long month = 111L;
        final Long day = 17L;
        assertFalse(isValidDate(year, month, day));
    }

    @Test
    public void dateWithMonthLessThanFirstOneShouldNotBeValid() {
        final Long year = 1996L;
        final Long month = 0L;
        final Long day = 17L;
        assertFalse(isValidDate(year, month, day));
    }

    @Test
    public void dateWithDayLessThanTheFirstOneShouldNotBeValid() {
        final Long year = 1996L;
        final Long month = 4L;
        final Long day = -111L;
        assertFalse(isValidDate(year, month, day));
    }


    @Test
    public void dateWithDayBiggerThanLastOneOfFebruaryInLeapYearShouldNotBeValid() {
        final Long year = 2016L;
        final Long month = 2L;
        final Long day = 30L;
        assertFalse(isValidDate(year, month, day));
    }

    @Test
    public void dateWithDayBiggerThanLastOneOfFebruaryShouldNotBeValid() {
        final Long year = 2017L;
        final Long month = 2L;
        final Long day = 29L;
        assertFalse(isValidDate(year, month, day));
    }

    @Test
    public void dateWithTwentyNineOfFebruaryInLeapYearShouldBeValid() {
        final Long year = 2016L;
        final Long month = 2L;
        final Long day = 29L;
        assertTrue(isValidDate(year, month, day));
    }

}
