package util.extraction.type;

import util.extraction.type.ValidBigDecimalExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ValidBigDecimalExtractorTest {

    @InjectMocks
    ValidBigDecimalExtractor extractor;

    @Test
    public void shouldExtractBigDecimalValueForValidInput() {
        final String inputValue = "42";
        final BigDecimal expected = new BigDecimal(42);

        final BigDecimal actual = extractor.getTypeFromInput(inputValue);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldRetunNullOnGetFromIllegalInput() {
        final String inputValue = "arst";
        final BigDecimal expected = null;

        final BigDecimal actual = extractor.getTypeFromInput(inputValue);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldRetunrNullOnGetFromEmplyInput() {
        final String inputValue = "";
        final BigDecimal expected = null;

        final BigDecimal actual = extractor.getTypeFromInput(inputValue);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnTrueOnIsValidForValidInput() {
        final boolean expected = true;
        final BigDecimal expectedDecimal = new BigDecimal(42);

        extractor.setValue(expectedDecimal);

        final boolean actual = extractor.isValid();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnFalseOnIsValidForNullValue() {
        final boolean expected = false;
        final BigDecimal expectedDecimal = null;

        extractor.setValue(expectedDecimal);

        final boolean actual = extractor.isValid();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldRetrunTrueOnValidateForValidNumber() {
        extractor.setValue(new BigDecimal(42));
        final boolean expected = true;

        final boolean actual = extractor.validate();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnFalseOnValidateForInvalidNumber() {
        extractor.setValue(new BigDecimal(-42));
        final boolean expected = false;

        final boolean actual = extractor.validate();

        assertThat(actual, equalTo(expected));
    }
}
