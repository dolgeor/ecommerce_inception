package util.extraction.type;

import util.extraction.Extractor;
import util.input.InputReader;
import util.validation.AbstractValidator;

import java.math.BigDecimal;

public class ValidBigDecimalExtractor extends Extractor<BigDecimal> {

    public ValidBigDecimalExtractor(InputReader inputReader) {
        super(inputReader);
    }

    @Override
    public BigDecimal getTypeFromInput(String answer) {
        value = null;
        try {
            Double valueInDouble = Double.parseDouble(answer);
            value = new BigDecimal(valueInDouble);
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Invalid number, try again ...");
        }
        return value;
    }

    @Override
    public boolean isValid() {
        return AbstractValidator.isNotNull(value) && validate();
    }

    protected boolean validate() {
        Double doubleValue = value.doubleValue();
        return doubleValue > 0;
    }
}
