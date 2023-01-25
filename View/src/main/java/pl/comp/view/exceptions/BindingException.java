package pl.comp.view.exceptions;

public class BindingException extends NoSuchMethodException{
    public BindingException() {
    }

    public BindingException(String s) {
        super(s);
    }
}
