package pl.comp.model;

import pl.comp.model.exceptions.FileException;
import pl.comp.model.exceptions.JdbcException;

public interface Dao<T> extends AutoCloseable {
    T read() throws FileException, JdbcException;

    void write(T obj) throws FileException, JdbcException;
}
