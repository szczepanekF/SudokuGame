package pl.first.firstjava;

public class SudokuElement {

    private final SudokuField[] element;
    private static final int N = 9;

    public SudokuElement() {
        element = new SudokuField[N];
        for (int i = 0;i < N; i++) {
            element[i] = new SudokuField();
        }
    }

    public int getValue(int i) {

        if (i >= 0 && i < 9) {
            return element[i].getFieldValue();
        } else {
            throw new IllegalArgumentException("Indexes are 0-8");
        }
    }

    public void setValue(int i, int val) {
        if (i >= 0 && i <= 9) {
            element[i].setFieldValue(val);
        } else {
            throw new IllegalArgumentException("Values are 0-9");
        }
    }

    //    public void print() {
    //        for (int i = 0;i < N;i++) {
    //            System.out.print(element[i].getFieldValue() + " ");
    //        }
    //    }

    public boolean verify() {
        boolean[] checkArray = new boolean[9];
        for (int i = 0;i < N; i++) {
            if (element[i].getFieldValue() != 0) {
                if (checkArray[element[i].getFieldValue() - 1]) {
                    return false;
                } else {
                    checkArray[element[i].getFieldValue() - 1] = true;
                }
            }
        }
        return true;
    }


}
