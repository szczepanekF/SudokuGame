package pl.first.firstjava;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(value,((SudokuField) obj).value).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("field",value).toString();
    }

}
