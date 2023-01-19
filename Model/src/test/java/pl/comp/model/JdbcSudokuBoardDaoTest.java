package pl.comp.model;

import org.junit.jupiter.api.Test;
import pl.comp.model.exceptions.FileException;
import pl.comp.model.exceptions.JdbcException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

    @Test
    void JdbcTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard board1 = new SudokuBoard(solver);
         board1.solveGame();
        SudokuBoard board2;
        try(Dao<SudokuBoard> dao = factory.getJdbcDao("sudoku testowe")){
            assertDoesNotThrow(()->dao.write(board1));



        } catch (Exception e) {

        }

        try(Dao<SudokuBoard> dao = factory.getJdbcDao("sudoku testowe")){
            assertDoesNotThrow(()->dao.read());
            board2 = dao.read();
            assertTrue(board1.equals(board2));
            assertFalse(board1 == board2);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void connectionTest(){
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard board1 = new SudokuBoard(solver);



        try(Dao<SudokuBoard> dao = factory.getJdbcDao("nieistniejace.sudoku")){
            assertThrows(JdbcException.class, dao::read);
        } catch (Exception e) {


        }

    }



}