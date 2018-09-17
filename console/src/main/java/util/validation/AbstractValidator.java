package util.validation;

public class AbstractValidator {

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) { return !isNull(value); }
}
