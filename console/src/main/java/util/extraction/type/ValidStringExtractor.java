package util.extraction.type;

import util.extraction.Extractor;
import util.input.InputReader;
import util.validation.AbstractValidator;
import util.validation.type.StringValidator;


public class ValidStringExtractor extends Extractor<String> {

    public ValidStringExtractor(InputReader inputReader) {
        super(inputReader);
    }

    @Override
    public String getTypeFromInput(String answer) {
        value = answer;
        return value;
    }

    @Override
    public boolean isValid() {
        return AbstractValidator.isNotNull(value) && StringValidator.isNotEmpty(value);
    }
}
