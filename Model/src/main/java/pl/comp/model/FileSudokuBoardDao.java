package pl.comp.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    FileOutputStream fout;
    FileInputStream fin;
    ObjectOutputStream oos;
    ObjectInputStream oin;


    public FileSudokuBoardDao(String filename) {
        try {
            fout = new FileOutputStream(filename);
            fin = new FileInputStream(filename);
            oos = new ObjectOutputStream(fout);
            oin = new ObjectInputStream(fin);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public SudokuBoard read() throws Exception {
        try {
            return (SudokuBoard) oin.readObject();
        } catch (Exception e) {
            //            e.printStackTrace();
            throw new Exception("File error");

        }
    }

    @Override
    public void write(SudokuBoard obj) throws Exception {
        try {
            oos.writeObject(obj);
        } catch (Exception e) {
            //            e.printStackTrace();
            throw new Exception("Object error");
        }
    }

    @Override
    public void close() throws Exception {
        fout.close();
        fin.close();
        oin.close();
        oos.close();
    }
}
