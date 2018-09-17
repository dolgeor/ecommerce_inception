package util.extraction.userdefined;

import util.extraction.Extractor;
import util.input.InputReader;

import java.math.BigDecimal;

public class ValidPriceExtractor extends Extractor<BigDecimal> {

    Extractor<BigDecimal> extractor;

    public ValidPriceExtractor(InputReader inputReader, Extractor<BigDecimal> extractor) {
        super(inputReader);
        this.extractor = extractor;
        extractor.setValue(value);
    }

    @Override
    public BigDecimal getTypeFromInput(String answer) {
        value = extractor.getTypeFromInput(answer);
        return value;
    }
    @Override
    public boolean isValid() {
        return extractor.isValid() && validate();
    }

    protected boolean validate() {
        int decimalComparator = value.compareTo(new BigDecimal(0));
        return decimalComparator >= 0;
    }
}
