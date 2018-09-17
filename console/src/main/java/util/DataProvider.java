package util;

import util.extraction.Extractor;
import util.extraction.type.ValidBigDecimalExtractor;
import util.extraction.type.ValidLongExtractor;
import util.extraction.type.ValidStringExtractor;
import util.extraction.userdefined.ValidAgeExtractor;
import util.extraction.userdefined.ValidDateExtractor;
import util.extraction.userdefined.ValidIdExtractor;
import util.extraction.userdefined.ValidPriceExtractor;
import util.input.InputReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class DataProvider {

    private InputReader inputReader;
    private Map<String, Extractor> validators;

    @Autowired
    public DataProvider(InputReader inputReader) {
        this.inputReader = inputReader;
        init();
    }

    private void init() {
        validators = new HashMap<>();

        validators.put("string", new ValidStringExtractor(inputReader));
        validators.put("long", new ValidLongExtractor(inputReader));
        validators.put("bigdecimal", new ValidBigDecimalExtractor(inputReader));

        validators.put("id", new ValidIdExtractor(inputReader, new ValidLongExtractor(inputReader)));
        validators.put("age", new ValidAgeExtractor(inputReader, new ValidLongExtractor(inputReader)));
        validators.put("price", new ValidPriceExtractor(inputReader, new ValidBigDecimalExtractor(inputReader)));
        validators.put("date", new ValidDateExtractor(inputReader, new ValidStringExtractor(inputReader)));
    }

    public Extractor getExtractor(String key) {
        return validators.get(key.toLowerCase());
    }

}
