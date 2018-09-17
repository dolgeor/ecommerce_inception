package util.extraction.userdefined;

import util.extraction.Extractor;
import util.input.InputReader;
import util.validation.userdefined.DateValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidDateExtractor extends Extractor<String> {
    private Extractor<String> stringExtractor;

    public ValidDateExtractor(InputReader inputReader, Extractor<String> stringExtractor) {
        super(inputReader);
        this.stringExtractor = stringExtractor;
    }

    @Override
    public String getTypeFromInput(String answer) {
        value = stringExtractor.getTypeFromInput(answer);
        return value;
    }

    @Override
    public boolean isValid() {
        Pattern pt = Pattern.compile("[0-9]{4}-[0-1]{1}[0-9]{1}-[0-3]{1}[0-9]{1}");
        if(!stringExtractor.isValid()) return false;

        Matcher matcher = pt.matcher(value);
        if(!matcher.matches()) return false;

        boolean isOk;
        long[] date = new long[3];// date{[yyyy],[mm],[dd]}
        String[] sDate = value.split("-");

        for(int i = 0; i < sDate.length; i++){
            date[i]=Long.parseLong(sDate[i]);
        }
        isOk = DateValidator.isValidDate(date[0],date[1],date[2]);
        return isOk;

    }

}
