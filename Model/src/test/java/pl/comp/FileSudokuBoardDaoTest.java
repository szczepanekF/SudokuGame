package pl.comp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {
    @Test
    void FileSudokuBoardTest(){
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard board1 = new SudokuBoard(solver);
        for (int i =0 ;i<9;i++){
            for (int j =0 ;j<9;j++) {
                board1.set(i,j,j+1);
            }
        }
        SudokuBoard board2;
        try(Dao<SudokuBoard> dao = factory.getFileDao("test.txt")){
            dao.write(board1);
            board2 = dao.read();
            assertTrue(board1.equals(board2));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void  FileSudokuBoardExceptionTest() {

        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        try(Dao<SudokuBoard> dao = factory.getFileDao("test2.txt")){
            assertThrows(Exception.class, dao::read);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}