package util.validation.type;

import util.validation.AbstractValidator;

public class LongValidator extends AbstractValidator {

    public static boolean isBiggerThanZero(Long value) {
        return value > 0;
    }

    public static boolean isLessThanMaxAge(Long value) {
        return value < 150;
    }
}
