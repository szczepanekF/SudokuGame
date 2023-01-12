package pl.comp.model.exceptions;

public class BadValueException extends IllegalArgumentException {
    public BadValueException(String s) {
        super(s);
    }
}
