package pl.first.firstjava;


import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private static final int N = 9;

    @Override
    public void solve(SudokuBoard board) {
        fillZeros(board);
        fillRest(board);
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
                        if (board.sudokuSet(row,col,num)) {
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
