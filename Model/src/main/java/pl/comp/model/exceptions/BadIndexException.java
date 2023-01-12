package pl.comp.model.exceptions;

public class BadIndexException extends IllegalArgumentException {
    public BadIndexException(String s) {
        super(s);
    }
}
