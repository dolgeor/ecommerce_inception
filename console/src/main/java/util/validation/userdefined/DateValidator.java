package util.validation.userdefined;

import util.validation.AbstractValidator;

public class DateValidator extends AbstractValidator {
    private static final long months[] = {0L,
            31L, 28L, 31L,
            30L, 31L, 30L,
            31L, 31L, 30L,
            31L, 30L, 31L};

    private final static Long FEBRUARY = 2L;

    public static boolean isLeapYear(Long year) {
        return isDivisible(year, 4) && isNotDivisible(year, 100) || isDivisible(year, 400);
    }

    public static boolean isValidDate(Long year, Long month, Long day) {
        return isValidMonth(month) && isValidDay(day,month,year);

    }

    private static boolean isValidDay(long day, long month, long year) {
        long endOfMonth = months[(int) month];
        if (isLeapYear(year) && month == FEBRUARY)
            endOfMonth++;
        return isBetween(day, 1L, endOfMonth);
    }

    private static boolean isValidMonth(Long month) {
        return isBetween(month, 1L, 12L);
    }

    private static boolean isBetween(Long value, Long begin, Long end) {
        return value >= begin && value <= end;
    }

    private static boolean isDivisible(Long year, int numberOfYears) {
        return year % numberOfYears == 0;
    }

    private static boolean isNotDivisible(Long year, int numberOfYears) {
        return !isDivisible(year, numberOfYears);
    }
}
