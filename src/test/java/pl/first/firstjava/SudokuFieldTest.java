package pl.first.firstjava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    SudokuField field;
    SudokuField field2;

    @BeforeEach
    public void init() {
        field = new SudokuField();
        field2 = new SudokuField();
        field2.setFieldValue(9);
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

    @Test
    void hashGetterTest(){
        assertEquals(field.hashCode(),field.hashCode());
        assertNotEquals(field.hashCode(),field2.hashCode());
    }


    @Test
    void equalsTest(){
        assertTrue(field.equals(field));
        assertFalse(field.equals(field2) || field.hashCode() == field2.hashCode());
    }

    @Test
    void toStringTest(){
        assertEquals(field.toString(),field.toString());
        assertNotEquals(field.toString(),field2.toString());
    }
}