package pl.comp.model.exceptions;

public class NullException extends NullPointerException {
    public NullException() {
    }

    public NullException(String s) {
        super(s);
    }
}
