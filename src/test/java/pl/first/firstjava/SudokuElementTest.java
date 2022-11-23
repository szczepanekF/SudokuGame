package pl.first.firstjava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementTest {

    Random rand;
    int num1;
    int num2;
    SudokuElement element;
    SudokuElement element2;
    SudokuBox box;
    SudokuColumn column;
    SudokuRow row;


    @BeforeEach
    public void init() {
        element = new SudokuElement();
        element2 = new SudokuElement();
        box = new SudokuBox();
        row = new SudokuRow();
        column = new SudokuColumn();
        rand = new Random();
        num1 = rand.nextInt(9);
        num2 = rand.nextInt(10);

        for (int i = 0;i < 9; i++) {
            element2.setValue(i,i);
        }

    }


    @Test
    void getterTest() {

        for (int i = 0; i < 9;i++) {
            assertEquals(element.getValue(i),0);
        }
    }

    @Test
    void getterFailureTest() {

        assertThrows(IllegalArgumentException.class,()->element.getValue(-1));
        assertThrows(IllegalArgumentException.class,()->element.getValue(10));
    }

    @Test
    void setterTest() {
        element.setValue(num1,num2);
        assertEquals(element.getValue(num1),num2);
    }

    @Test
    void setterFailuireTest() {
        assertThrows(IllegalArgumentException.class,()->element.setValue(-1,9));
        assertThrows(IllegalArgumentException.class,()->element.setValue(-1,-1));
        assertThrows(IllegalArgumentException.class,()->element.setValue(10,10));
        assertThrows(IllegalArgumentException.class,()->element.setValue(10,9));
    }

    @Test
    void verifyTest() {
        assertTrue(element.verify());
        for (int i = 0;i < 9;i++){
            element.setValue(i,i+1);
        }
        assertTrue(element.verify());
    }

    @Test
    void verifyFailureTest() {
        element.setValue(0,1);
        element.setValue(1,1);
        assertFalse(element.verify());
    }

    @Test
    void hashGetterTest(){
        assertEquals(element.hashCode(), element.hashCode());
        assertNotEquals(element.hashCode(),element2.hashCode());
    }


    @Test
    void equalsTest(){
        assertTrue(element.equals(element));
        assertFalse(element.equals(element2) || element.hashCode() == element2.hashCode());
    }

    @Test
    void toStringTest(){
        assertNotNull(element.toString());
    }

}