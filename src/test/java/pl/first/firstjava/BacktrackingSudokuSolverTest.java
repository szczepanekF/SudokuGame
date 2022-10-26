package pl.first.firstjava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {


    SudokuBoard board;
    SudokuSolver solver;

    @BeforeEach
    public void init() {
        solver = new BacktrackingSudokuSolver();
        board = new SudokuBoard(solver);
        board.solveGame();

    }


    @Test
    void isBoardSafe(){


        int box_row;
        int box_col;
        for(int i = 0; i<9;i++){
            for(int j=0;j<9;j++)
            {
                for(int k=0;k<9;k++) {
                    if(k!=i)
                            assertNotEquals(board.get(k,j), board.get(i,j));
                    if(k!=j)
                            assertNotEquals(board.get(i,k), board.get(i,j));
                }

                box_row = i - i%3;
                box_col = j - j%3;
                for(int k =0;k<3;k++)
                    for(int m=0;m<3;m++)
                        if(box_row+k != i && box_col+m != j) {
                            assertNotEquals(board.get(box_row+k, box_col+m), board.get(i, j));

                        }
            }
        }
       }

    public void copy(int[][] board1) {
        for(int i = 0;i < 9;i++) {
            for(int j = 0; j < 9; j++) {
                board1[i][j] = board.get(i,j);
            }
        }
    }

    @Test
    void isDifferentEverytime(){

        int[][] board1=new int[9][9];
        int[][] board2=new int[9][9];
        int[][] board3=new int[9][9];

        copy(board1);

        board.solveGame();

        copy(board2);

        board.solveGame();

        copy(board3);

        int equalcount1 = 0;
        int equalcount2 = 0;
        int equalcount3 = 0;

        for(int i = 0; i < 9; i++) {

            if(Arrays.equals(board1[i],board2[i])) {

                equalcount1++;
            }

            if(Arrays.equals(board2[i],board3[i])) {
                equalcount2++;
            }

            if(Arrays.equals(board1[i],board3[i])) {
                equalcount3++;
            }

        }

        assertTrue(equalcount1<9);
        assertTrue(equalcount2<9);
        assertTrue(equalcount3<9);
    }

}