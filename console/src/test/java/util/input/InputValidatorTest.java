package util.input;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.input.InputReader;

import java.io.BufferedReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InputValidatorTest {

    @Mock
    BufferedReader bufferedReader;


    @InjectMocks
    InputReader inputReader;

    @Test
    public void shouldSuccesfullyGetInput() {
        final String input = "42";
        final String expected = "42";

        try {
            when(bufferedReader.readLine()).thenReturn(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String actual = inputReader.getInput();

        assertThat(actual, equalTo(expected));
    }
}
