package pl.first.firstjava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    SudokuField field;

    @BeforeEach
    public void init() {
        field = new SudokuField();
    }


    @Test
    void getterTest() {
        assertEquals(field.getFieldValue(),0);
    }

    @Test
    void setterTest() {
        assertEquals(field.getFieldValue(),0);
        field.setFieldValue(5);
        assertEquals(field.getFieldValue(),5);
    }

    @Test
    void setterFailureTest() {
        assertThrows(IllegalArgumentException.class, () -> field.setFieldValue(10));
        assertEquals(field.getFieldValue(),0);
        assertThrows(IllegalArgumentException.class, () -> field.setFieldValue(-1));
        assertEquals(field.getFieldValue(),0);
    }
}