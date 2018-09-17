package util.extraction.userdefined;

import util.extraction.Extractor;
import util.input.InputReader;
import util.validation.type.LongValidator;


public class ValidIdExtractor extends Extractor<Long> {

    Extractor<Long> extractor;

    public ValidIdExtractor(InputReader inputReader, Extractor<Long> extractor) {
        super(inputReader);
        this.extractor = extractor;
    }

    @Override
    public Long getTypeFromInput (String answer){
        value = extractor.getTypeFromInput(answer);
        return value;
    }

    @Override
    public boolean isValid() {
        return extractor.isValid() && LongValidator.isBiggerThanZero(value);
    }
}