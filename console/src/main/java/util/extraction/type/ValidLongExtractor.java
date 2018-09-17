package util.extraction.type;

import util.extraction.Extractor;
import util.input.InputReader;
import util.validation.AbstractValidator;

public class ValidLongExtractor extends Extractor<Long> {

    public ValidLongExtractor(InputReader inputReader) {
        super(inputReader);
    }

    @Override
    public Long getTypeFromInput(String answer) {
        try {
            value = null;
            value = Long.parseLong(answer);
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("It's not real number, try again ...");
        }
        return value;
    }

    @Override
    public boolean isValid() {
        return AbstractValidator.isNotNull(value);
    }
}
