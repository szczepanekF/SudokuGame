package pl.comp.model;

import java.io.Serializable;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.comp.model.exceptions.BadValueException;
import pl.comp.model.exceptions.NullException;


public class SudokuField implements Serializable,Cloneable,Comparable<SudokuField> {


    private int value;

    public int getFieldValue() {

        return value;
    }

    public void setFieldValue(int val) {

        if (val >= 0 && val <= 9) {
            this.value = val;
        } else {
            throw new BadValueException(ResourceBundle.getBundle("pl.comp.model.Exceptions")
                    .getObject("!wrong_value").toString());
        }

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
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
        return new EqualsBuilder().append(value,((SudokuField) obj).getFieldValue()).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("field",value).toString();
    }

    @Override
    public int compareTo(SudokuField o)throws NullException {
        if (o == null) {
            throw new NullException();
        }
        return getFieldValue() - o.getFieldValue();
    }

    @Override
    protected SudokuField clone() throws CloneNotSupportedException {

        return (SudokuField) super.clone();
    }
}
