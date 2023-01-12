package pl.comp.view.exceptions;

import java.io.IOException;

public class SceneLoadingException extends IOException {
    public SceneLoadingException() {
    }

    public SceneLoadingException(String message) {
        super(message);
    }

    public SceneLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public SceneLoadingException(Throwable cause) {
        super(cause);
    }
}
