package util.extraction.userdefined;

import util.extraction.Extractor;
import util.input.InputReader;
import util.validation.type.LongValidator;


public class ValidAgeExtractor extends Extractor<Long> {

    Extractor<Long> extractor;

    public ValidAgeExtractor(InputReader inputReader, Extractor<Long> extractor) {
        super(inputReader);
        this.extractor = extractor;
        extractor.setValue(this.value);
    }

    @Override
    public Long getTypeFromInput (String answer){
        value = extractor.getTypeFromInput(answer);
        return value;
    }

    @Override
    public boolean isValid() {
            return extractor.isValid() && LongValidator.isBiggerThanZero(value) && LongValidator.isLessThanMaxAge(value);
    }
}