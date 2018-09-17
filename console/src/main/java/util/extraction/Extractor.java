package util.extraction;

import util.input.InputReader;

public abstract class Extractor<T> {
    protected InputReader inputReader;
    protected T value;

    public Extractor(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public T get(String ask) {
        while (true) {
            System.out.print(ask);
            value = getTypeFromInput(getInputAnswer());
            if (isValid())
                break;
            System.out.println("Wrong input.");
        }
        return value;
    }

    private String getInputAnswer() {
        return inputReader.getInput();
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public abstract T getTypeFromInput(String answer);

    public abstract boolean isValid();
}
