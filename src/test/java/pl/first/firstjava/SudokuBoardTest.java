package pl.first.firstjava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

   SudokuBoard board;
    SudokuBoard board2;
   SudokuSolver solver;
   Random rand;
   int num1;
   int num2;
   int a;
   @BeforeEach
   public void init() {
       solver = new BacktrackingSudokuSolver();
       board = new SudokuBoard(solver);
       board2 = new SudokuBoard(solver);
       board.solveGame();
       rand = new Random();
       num1 = rand.nextInt(9);
       num2 = rand.nextInt(9);
       a = board.get(num1,num2);
       a = a % 9;
       a++;
       for (int i =0 ;i<9;i++){
           for (int j =0 ;j<9;j++) {
               board2.set(i,j,j+1);
           }
       }
   }

    @Test
    void getRowTest() {
       SudokuRow row;
       row = board2.getRow(0);
       for (int i =0 ;i<9;i++) {
           assertEquals(row.getValue(i),i+1);
       }
    }
    @Test
    void getColumnTest() {
       SudokuColumn column;
       column = board2.getColumn(0);
        for (int i =0 ;i<9;i++) {
            assertEquals(column.getValue(i),1);
        }
    }

    @Test
    void getBoxTest() {
       SudokuBox box;
       box = board2.getBox(0,0);
        for (int i =0 ;i<3;i++) {
            assertEquals(box.getValue(i),i + 1);
        }
    }


    @Test
    void getBoxFailureTest() {
        assertThrows(IllegalArgumentException.class,()->board.getBox(-1,0));
        assertThrows(IllegalArgumentException.class,()->board.getBox(0,-1));
        assertThrows(IllegalArgumentException.class,()->board.getBox(0,9));
        assertThrows(IllegalArgumentException.class,()->board.getBox(9,0));
        assertThrows(IllegalArgumentException.class,()->board.getBox(-1,-1));
        assertThrows(IllegalArgumentException.class,()->board.getBox(9,9));
    }

    @Test
    void getRowFailureTest() {
       assertThrows(IllegalArgumentException.class,()->board.getRow(9));
        assertThrows(IllegalArgumentException.class,()->board.getRow(-11));
    }

    @Test
    void getColumnFailureTest() {
       assertThrows(IllegalArgumentException.class,()->board.getColumn(9));
       assertThrows(IllegalArgumentException.class,()->board.getColumn(-1));
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

    @Test
    void hashGetterTest(){
       assertEquals(board.hashCode(),board.hashCode());
       assertNotEquals(board.hashCode(),board2.hashCode());
    }


    @Test
    void equalsTest(){
        assertTrue(board.equals(board));
        assertFalse(board.equals(board2) || board.hashCode() == board2.hashCode());
    }

    @Test
    void toStringTest(){
        assertNotNull(board.toString());
    }

}