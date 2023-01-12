package pl.comp.model;

public class SudokuBoardDaoFactory {


    public Dao<SudokuBoard> getFileDao(String filename) {

        return new FileSudokuBoardDao(filename);

        }
    }

