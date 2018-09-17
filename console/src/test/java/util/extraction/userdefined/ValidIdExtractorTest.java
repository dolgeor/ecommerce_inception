package util.extraction.userdefined;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.extraction.type.ValidLongExtractor;
import util.extraction.userdefined.ValidIdExtractor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidIdExtractorTest {

    @Mock
    ValidLongExtractor validLongExtractor;

    @InjectMocks
    ValidIdExtractor validIdExtractor;

    @Test
    public void shouldGetTypeOnValidInput() {
        final String input = "42";
        final Long expected = 42L;

        when(validLongExtractor.getTypeFromInput(input)).thenReturn(expected);

        final Long actual = validIdExtractor.getTypeFromInput(input);

        assertThat(actual, equalTo(expected));
    }
}
