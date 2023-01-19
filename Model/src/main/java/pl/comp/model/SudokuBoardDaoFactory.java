package pl.comp.model;

public class SudokuBoardDaoFactory {


    public Dao<SudokuBoard> getFileDao(String filename) {

        return new FileSudokuBoardDao(filename);

    }

    public Dao<SudokuBoard> getJdbcDao(String sudokuname) {
        return new JdbcSudokuBoardDao(sudokuname);
    }

}

