package util.validation.type;

import util.validation.AbstractValidator;

public class StringValidator extends AbstractValidator {

    public static boolean isNotEmpty(String value) {
        return !value.isEmpty();
    }

}
