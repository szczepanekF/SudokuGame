package pl.comp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    SudokuField field;
    SudokuField field2;
    SudokuField field3;

    @BeforeEach
    public void init() {
        field = new SudokuField();
        field2 = new SudokuField();
        field3 = new SudokuField();
        field2.setFieldValue(9);
        field3.setFieldValue(9);
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
        assertNotNull(field.hashCode());
        assertEquals(field.hashCode(),field.hashCode());
        assertNotEquals(field.hashCode(),field2.hashCode());
    }

    @Test
    void equalsTest(){
        assertTrue(field.equals(field));
        assertFalse(field.equals(null));
        assertFalse(field.equals(new int[1]));
        assertTrue(field2.equals(field3));
    }

    @Test
    void equalsHashCodeTest(){
        assertTrue(field2.equals(field3) && field2.hashCode() == field3.hashCode());
    }

    @Test
    void toStringTest(){
        assertNotNull(field.toString());
        assertEquals(field.toString(),field.toString());
        assertNotEquals(field.toString(),field2.toString());
    }
}