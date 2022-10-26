package pl.first.firstjava;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private static final int N = 9;

    @Override
    public void solve(SudokuBoard board) {
        fillZeros(board);
        fillRest(board);
    }

    private boolean isSafe(int row,int col,int num,SudokuBoard board) {
        for (int i = 0;i < N; i++) {
            if (board.get(i,col) == num) {
                return false;
            }
        }

        for (int i = 0;i < N; i++) {
            if (board.get(row,i) == num) {
                return false;
            }
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = 0;i < 3;i++) {
            for (int j = 0;j < 3;j++) {
                if (board.get(boxRow + i,boxCol + j) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private void fillZeros(SudokuBoard board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board.set(i,j,0);
            }
        }
    }

    private boolean fillRest(SudokuBoard board) {
        Random rand = new Random();
        int num;

        for (int row = 0;row < N;row++) {
            for (int col = 0;col < N;col++) {
                if (board.get(row,col) == 0) {
                    num = rand.nextInt(N) + 1;
                    for (int i = 1; i <= N; i++) {
                        if (isSafe(row, col, num,board)) {
                            board.set(row,col,num);
                            if (fillRest(board)) {
                                return true;
                            } else {
                                board.set(row,col,0);
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


}
