package pl.comp.model;

import pl.comp.model.exceptions.FileException;

public interface Dao<T> extends AutoCloseable {
    T read() throws FileException;

    void write(T obj) throws FileException;
}
