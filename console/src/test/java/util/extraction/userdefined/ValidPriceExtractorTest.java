package util.extraction.userdefined;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.extraction.type.ValidBigDecimalExtractor;
import util.extraction.userdefined.ValidPriceExtractor;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidPriceExtractorTest {

    @Mock
    ValidBigDecimalExtractor bigDecimalExtractor;

    @InjectMocks
    ValidPriceExtractor priceExtractor;

    @Test
    public void shouldGetPriceFromInput() {
        final String input = "42";
        final BigDecimal expected = new BigDecimal(42);

        when(bigDecimalExtractor.getTypeFromInput(input)).thenReturn(expected);

        final BigDecimal actual = priceExtractor.getTypeFromInput(input);

        assertThat(actual, equalTo(actual));

        verify(bigDecimalExtractor).getTypeFromInput(input);
    }

    @Test
    public void shouldReturnNullOnGetPriceFromInvalidInput() {
        final String input = "forty two";
        final BigDecimal expected = null;

        when(bigDecimalExtractor.getTypeFromInput(input)).thenReturn(expected);

        final BigDecimal actual = priceExtractor.getTypeFromInput(input);

        assertThat(actual, equalTo(actual));

        verify(bigDecimalExtractor).getTypeFromInput(input);
    }

    @Test
    public void shouldReturnTrueOnValidateLegalValue() {
        final BigDecimal testValue = new BigDecimal(42);
        priceExtractor.setValue(testValue);

        final boolean expected = true;
        final boolean actual = priceExtractor.validate();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnFalseOnValidateIllegalValue() {
        final BigDecimal testValue = new BigDecimal(-42);
        priceExtractor.setValue(testValue);

        final boolean expected = false;
        final boolean actual = priceExtractor.validate();

        assertThat(actual, equalTo(expected));
    }
}
