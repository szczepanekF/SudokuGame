package pl.comp.model.exceptions;

import java.io.IOException;

public class DaoException extends IOException {

    public DaoException() {
    }


    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
