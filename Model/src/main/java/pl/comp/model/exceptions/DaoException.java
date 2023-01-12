package pl.comp.model.exceptions;

import java.io.IOException;

public class DaoException extends IOException {
    public DaoException(Throwable cause) {
        super(cause);
    }
}
