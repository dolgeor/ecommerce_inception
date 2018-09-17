package util.input;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReader {
    @Autowired
    private BufferedReader bufferedReader;

    public String getInput() {
        String input = null;
        try {
            input = bufferedReader.readLine().trim();
        } catch (IOException e) {
            System.out.println("Illegal input ...");
        }
        return input;
    }
}
