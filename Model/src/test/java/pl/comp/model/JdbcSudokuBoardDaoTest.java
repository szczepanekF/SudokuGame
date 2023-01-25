package pl.comp.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.comp.model.exceptions.FileException;
import pl.comp.model.exceptions.JdbcException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

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
    void transactionTest(){
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SudokuBoard board1 = new SudokuBoard(solver);
        board1.solveGame();
        try(JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) factory.getJdbcDao("nieistniejace.sudoku")){
           doThrow(SQLException.class).when(dao).insert(any(PreparedStatement.class),4,5,any(Integer.class));
           assertThrows(JdbcException.class, () -> dao.write(board1));
           assertThrows(JdbcException.class, dao::read, ResourceBundle.getBundle("pl.comp.model.Exceptions").getObject("!wrong_sudoku_name").toString());
        } catch (Exception e) {

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