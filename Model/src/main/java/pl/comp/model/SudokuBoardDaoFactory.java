package pl.comp.model;

public class SudokuBoardDaoFactory {


    Dao<SudokuBoard> getFileDao(String filename) {

        return new FileSudokuBoardDao(filename);

        }
    }
