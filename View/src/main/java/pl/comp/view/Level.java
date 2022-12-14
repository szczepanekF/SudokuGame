package pl.comp.view;

import pl.comp.model.SudokuBoard;

import java.util.Random;

public enum Level {
    EASY(43),
    MEDIUM(51),
    HARD(59);



    private int fieldsToErase;
    Level(int howManyFields){
        this.fieldsToErase = howManyFields;
    }

    public int getFieldsToErase() {
        return fieldsToErase;
    }



    public void deleteFields(SudokuBoard board){
        Random rand = new Random();
        int i = fieldsToErase;
        int x,y;
        while(i > 0) {
            x = rand.nextInt(0, 9);
            y = rand.nextInt(0, 9);
            if (board.get(x,y) == 0) {
                continue;
            }
            board.set(x,y,0);
            i -= 1;
        }
    }
}
