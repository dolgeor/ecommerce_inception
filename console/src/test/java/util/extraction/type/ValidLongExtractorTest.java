package util.extraction.type;

import util.extraction.type.ValidLongExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidLongExtractorTest {
    @Mock
    ValidLongExtractor validLongExtractor;

    @Test
    public void shouldReturnValidPositiveLong() {
        final String input = "42";
        final Long expected = 42L;

        when(validLongExtractor.getTypeFromInput(input)).thenReturn(expected);

        final Long actual = validLongExtractor.getTypeFromInput(input);

        assertThat(actual, equalTo(expected));

        Mockito.verify(validLongExtractor).getTypeFromInput("42");
    }

}
