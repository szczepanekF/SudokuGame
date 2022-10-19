package pl.first.firstjava;

import java.util.Arrays;
import java.util.Random;

public class SudokuBoard {


    public int[][] board;
    private static final int N = 9;

    public int getBoard(int row,int col) {
        return board[row][col];
    }



    public SudokuBoard() {
        this.board = new int[N][N];
    }



    public void print() {
        for (int i = 0;i < N; i++) {
            for (int j = 0;j < N; j++) {
                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');

        }

    }

    public boolean isSafe(int row,int col,int num) {
        for (int i = 0;i < N; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        for (int i = 0;i < N; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = 0;i < 3;i++) {
            for (int j = 0;j < 3;j++) {
                if (board[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillZeros() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
    }

    public boolean fillRest() {
        Random rand = new Random();
        int num;

        for (int row = 0;row < N;row++) {
            for (int col = 0;col < N;col++) {
                if (board[row][col] == 0) {
                    num = rand.nextInt(N) + 1;
                    for (int i = 1; i <= N; i++) {
                        if (isSafe(row, col, num)) {
                            board[row][col] = num;
                            if (fillRest()) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                        num = num % N;
                        num++;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void fillBoard() {
        fillZeros();
        fillRest();
    }
}

