package pl.first.firstjava;


import java.util.Arrays;

public class SudokuBoard {

    private final SudokuSolver sudokuSolver;
    private final int[][] board;
    private static final int N = 9;

    public int get(int row, int col) {

        if (row < 9 && row >= 0 && col < 9 && col >= 0) {
            return board[row][col];
        } else {
            throw new IllegalArgumentException("Rows and Columns indexes are 0-8");
        }
    }

    public void set(int row,int col,int val) {
        if (row < 9 && row >= 0 && col < 9 && col >= 0) {
            if (val >= 0 && val <= 9) {
                board[row][col] = val;
            } else {
                throw new IllegalArgumentException("value is 0-9");
            }
        } else {
            throw new IllegalArgumentException("Rows and Columns indexes are 0-8");
        }
    }

    public SudokuBoard(SudokuSolver solver) {

        this.board = new int[N][N];
        sudokuSolver = solver;
    }

    public boolean check() {
        if (checkRows_Columns(true)) {
            if (checkRows_Columns(false)) {
                return checkBoxes();
            }
        }
        return false;
    }

    public boolean checkRows_Columns(boolean decision) {
        boolean[] checkArray = new boolean[9];
        int a;
        int b;
        for (int i = 0;i < N;i++) {
            for (int j = 0; j < N; j++) {
                if (decision) {
                    a = i;
                    b = j;
                } else {
                    b = i;
                    a = j;
                }
                if (checkArray[board[a][b] - 1]) {
                    return false;
                } else {
                    checkArray[board[a][b] - 1] = true;
                }
            }
            Arrays.fill(checkArray,false);
        }
        return true;
    }


    public boolean checkBoxes() {
        boolean[] checkArray = new boolean[9];
        for (int i = 0; i < N; i += 3) {
            for (int j = 0; j < N; j += 3) {
                for (int m = 0; m < 3; m++) {
                    for (int k = 0; k < 3;k++) {
                        if (checkArray[board[i + m][j + k] - 1]) {
                            return false;
                        } else {
                            checkArray[board[i + m][j + k] - 1] = true;
                        }
                    }
                }


                Arrays.fill(checkArray,false);
            }
        }

        return true;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    //    public void print() {
    //        for (int i = 0;i < N; i++) {
    //            for (int j = 0;j < N; j++) {
    //                System.out.print(board[i][j]);
    //                System.out.print(' ');
    //            }
    //            System.out.print('\n');
    //
    //        }
    //
    //    }


}

