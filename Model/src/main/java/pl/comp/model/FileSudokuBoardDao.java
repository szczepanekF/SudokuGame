package pl.comp.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.comp.model.exceptions.FileException;


public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    String filename;


    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws FileException {

        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return  (SudokuBoard) objectInputStream.readObject();
        } catch (IOException e) {
            throw new FileException(e);
        } catch (ClassNotFoundException e) {
            throw new FileException(e);
        }

    }


    @Override
    public void write(SudokuBoard obj) throws FileException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }


    @Override
    public void close() {

    }
}