package pl.first.firstjava;

public class SudokuField {

    private int value;

    public int getFieldValue() {

        return value;
    }

    public void setFieldValue(int val) {

        if (val >= 0 && val <= 9) {
            this.value = val;
        } else {
            throw new IllegalArgumentException("value is 0-9");
        }

    }
}
