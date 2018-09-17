package util.extraction.type;

import util.extraction.type.ValidStringExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ValidStringExtractorTest {

    @InjectMocks
    ValidStringExtractor validStringExtractor;

    @Test
    public void shouldReturnValidStirng() {
        final String input = "Forty two.";
        final String expected = input;

        final String actual = validStringExtractor.getTypeFromInput(input);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnNullOnEmpltyGetStringFromInput() {
        final String input = "";
        final String expected = "";

        final String actual = validStringExtractor.getTypeFromInput(input);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void shouldReturnFalseOnIsValidforRIllegalInput() {
        final String input = "";
        final boolean expected = false;

        final boolean actual = validStringExtractor.isValid();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void shouldReturnTrueOnIsValidLeggalInput() {
        final String input = "Forty two.";
        final boolean expected = true;

        validStringExtractor.setValue(input);

        final boolean actual = validStringExtractor.isValid();

        assertThat(actual, equalTo(expected));
    }
}
