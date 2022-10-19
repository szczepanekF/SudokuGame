package pl.first.firstjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    @Test
    void isBoardSafe(){
        var board = new SudokuBoard();
        board.fillBoard();
        int box_row;
        int box_col;
        for(int i = 0; i<9;i++){
            for(int j=0;j<9;j++)
            {
                for(int k=0;k<9;k++) {
                    if(k!=i)
                        assertNotEquals(board.getBoard(k,j), board.getBoard(i,j));
                    if(k!=j)
                        assertNotEquals(board.getBoard(i,k), board.getBoard(i,j));
                }

                box_row = i - i%3;
                box_col = j - j%3;
                for(int k =0;k<3;k++)
                    for(int m=0;m<3;m++)
                        if(box_row+k != i && box_col+m != j) {
                            assertNotEquals(board.getBoard(box_row+k, box_col+m), board.getBoard(i, j));

                        }
            }
        }
    }
    @Test
    void isDifferentEverytime(){
        SudokuBoard board = new SudokuBoard();
        int[][] board1=new int[9][9];
        int[][] board2=new int[9][9];
        int[][] board3=new int[9][9];

        board.fillBoard();

        for(int i = 0;i < 9;i++) {
            for(int j = 0; j < 9; j++) {
                board1[i][j] = board.getBoard(i,j);
            }
        }

        board.fillBoard();

        for(int i = 0;i < 9;i++) {
            for(int j = 0; j < 9; j++) {
                board2[i][j] =  board.getBoard(i,j);
            }
        }
        board.fillBoard();

        for(int i = 0;i < 9;i++) {
            for(int j = 0; j < 9; j++) {
                board3[i][j] = board.getBoard(i,j);
            }
        }
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