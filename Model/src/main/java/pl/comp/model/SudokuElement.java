package pl.comp.model;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuElement implements Cloneable{

    private final List<SudokuField> element;
    private static final int N = 9;

    public SudokuElement() {
        this.element = Arrays.asList(new SudokuField[N]);
        for (int i = 0;i < N; i++) {
            element.set(i,new SudokuField());
        }
    }

    public int getValue(int i) {

        if (i >= 0 && i < 9) {
            return element.get(i).getFieldValue();
        } else {
            throw new IllegalArgumentException("Indexes are 0-8");
        }
    }

    public void setValue(int i, int val) {
        if (i >= 0 && i <= 9) {
            element.get(i).setFieldValue(val);
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
            if (element.get(i).getFieldValue() != 0) {
                if (checkArray[element.get(i).getFieldValue() - 1]) {
                    return false;
                } else {
                    checkArray[element.get(i).getFieldValue() - 1] = true;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 31).append(element).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return new EqualsBuilder().append(element,((SudokuElement) obj).element).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("element",element).toString();
    }

    @Override
    protected SudokuElement clone() {
        SudokuElement ret = new SudokuElement();
        for (int i =0 ;i < N;i++){
            ret.setValue(i,getValue(i));
        }
        return ret;
    }
}
