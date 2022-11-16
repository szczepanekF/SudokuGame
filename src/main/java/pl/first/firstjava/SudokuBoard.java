package pl.first.firstjava;


import java.util.Arrays;
import java.util.List;

public class SudokuBoard {

    private final SudokuSolver sudokuSolver;

    private final List<List<SudokuField>> board;
    private static final int N = 9;

    public SudokuBoard(SudokuSolver solver) {

        this.board = Arrays.asList(new List[N]);

        for (int i = 0; i < N; i++) {
            board.set(i, Arrays.asList(new SudokuField[N]));
        }

        for (int i = 0;i < N;i++) {
            for (int y = 0;y < N;y++) {
                board.get(i).set(y, new SudokuField());
            }
        }
        sudokuSolver = solver;
    }

    public int get(int row, int col) {

        if (row < 9 && row >= 0 && col < 9 && col >= 0) {
            return board.get(row).get(col).getFieldValue();
        } else {
            throw new IllegalArgumentException("Rows and Columns indexes are 0-8");
        }
    }

    public void set(int row,int col,int val) {
        if (row < 9 && row >= 0 && col < 9 && col >= 0) {
            board.get(row).get(col).setFieldValue(val);
        } else {
            throw new IllegalArgumentException("Rows and Columns indexes are 0-8");
        }
    }

    public boolean sudokuSet(int row,int col,int val) {
        int a = get(row,col);
        set(row,col,val);
        if (!checkBoard()) {
            set(row,col,a);
            return false;
        }
        return true;
    }

    public SudokuRow getRow(int y) {

        if (y >= 0 && y < N) {
            SudokuRow row = new SudokuRow();
            for (int i = 0; i < N; i++) {
                row.setValue(i, board.get(y).get(i).getFieldValue());
            }

            return row;
        } else {
            throw new IllegalArgumentException("Rows indexes are 0-8");
        }
    }

    public SudokuColumn getColumn(int x) {
        if (x >= 0 && x < N) {
            SudokuColumn column = new SudokuColumn();
            for (int i = 0; i < N; i++) {
                column.setValue(i, board.get(i).get(x).getFieldValue());
            }

            return column;
        } else {
            throw new IllegalArgumentException("Columns indexes are 0-8");
        }
    }

    public SudokuBox getBox(int x, int y) {
        if (x < N && x >= 0 && y < N && y >= 0) {
            SudokuBox box = new SudokuBox();
            int n = 0;
            int boxX = x - x % 3;
            int boxY = y - y % 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    box.setValue(n, board.get(boxX + i).get(boxY + j).getFieldValue());
                    n++;
                }
            }
            return box;
        } else {
            throw new IllegalArgumentException("Rows and Columns indexes are 0-8");
        }
    }

    private boolean checkRows() {
        for (int i = 0;i < N;i++) {
            if (!getRow(i).verify()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumns() {
        for (int i = 0;i < N;i++) {
            if (!getColumn(i).verify()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBoxes() {
        for (int i = 0; i < N; i += 3) {
            for (int j = 0; j < N; j += 3) {
                if (!getBox(i,j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkBoard() {
        if (!checkColumns()) {
            return false;
        }
        if (checkRows()) {

            return checkBoxes();

        }
        return false;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    //    public void print() {
    //         for (int i = 0;i < N; i++) {
    //             for (int j = 0;j < N; j++) {
    //                 System.out.print(board[i][j].getFieldValue());
    //                 System.out.print(' ');
    //             }
    //             System.out.print('\n');
    //         }
    //    }


}

