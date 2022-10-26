package pl.first.firstjava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

   SudokuBoard board;
   SudokuSolver solver;
   Random rand;
   int num1;
   int num2;
   int a;
   @BeforeEach
   public void init() {
       solver = new BacktrackingSudokuSolver();
       board = new SudokuBoard(solver);
       board.solveGame();
       rand = new Random();
       num1 = rand.nextInt(9);
       num2 = rand.nextInt(9);
       a = board.get(num1,num2);
       a = a % 9;
       a++;
   }


    @Test
    void checkRowsTest() {
        assertTrue(board.checkRows_Columns(true));
        board.set(num1,num2,a);
        assertFalse(board.checkRows_Columns(true));
    }
    @Test
    void checkColumnsTest() {
        assertTrue(board.checkRows_Columns(false));
        board.set(num1,num2,a);
        assertFalse(board.checkRows_Columns(false));

    }
    @Test
    void checkBoxesTest() {
        assertTrue(board.checkBoxes());
        board.set(num1,num2,a);
        assertFalse(board.checkBoxes());
    }
    @Test
    void checkColumnsTestFailure() {
        assertTrue(board.check());

        for (int i = 0 ;i < 9;i++) {
            for (int j = 0; j < 9;j++){
                board.set(i,j,j + 1);
            }
        }
        assertFalse(board.check());

    }
    @Test
    void checkRowsTestFailure() {
        assertTrue(board.check());
        for (int i = 0 ;i < 9;i++) {
            for (int j = 0; j < 9;j++){
                board.set(i,j,i + 1);
            }
        }
        assertFalse(board.check());

    }

    @Test
    void getterTest() {
       SudokuBoard board1 = new SudokuBoard(solver);
       assertEquals(board1.get(num1,num2),0);
    }
    @Test
    void getterTestFailure() {
       assertThrows(IllegalArgumentException.class,() -> board.get(-1,0));
        assertThrows(IllegalArgumentException.class,() -> board.get(0,-1));
        assertThrows(IllegalArgumentException.class,() -> board.get(9,0));
        assertThrows(IllegalArgumentException.class,() -> board.get(0,9));

    }

    @Test
    void setterTest() {
       board.set(num1,num2,9);
        assertEquals(board.get(num1,num2),9);
    }

    @Test
    void setterTestFailure() {
        assertThrows(IllegalArgumentException.class,() -> board.set(-1,0,0));
        assertThrows(IllegalArgumentException.class,() -> board.set(0,-1,0));
        assertThrows(IllegalArgumentException.class,() -> board.set(0,0,-1));
        assertThrows(IllegalArgumentException.class,() -> board.set(0,0,10));
        assertThrows(IllegalArgumentException.class,() -> board.set(9,0,0));
        assertThrows(IllegalArgumentException.class,() -> board.set(0,9,0));

    }


}