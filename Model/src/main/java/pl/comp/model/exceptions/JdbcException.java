package pl.comp.model.exceptions;

import java.sql.SQLException;

public class JdbcException extends DaoException {
    public JdbcException(Throwable cause) {
        super(cause);
    }
}
