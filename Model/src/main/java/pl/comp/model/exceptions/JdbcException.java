package pl.comp.model.exceptions;


public class JdbcException extends DaoException {
    public JdbcException() {
    }

    public JdbcException(String message) {
        super(message);
    }

    public JdbcException(Throwable cause) {
        super(cause);
    }

}
