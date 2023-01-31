package pl.comp.view;

import java.util.Random;
import pl.comp.model.SudokuBoard;

public enum Level {
    EASY(41),
    MEDIUM(50),
    HARD(59);



    private final int numberOfBlankFields;

    Level(int howManyFields) {
        this.numberOfBlankFields = howManyFields;
    }

    public int getNumberOfBlankFields() {
        return numberOfBlankFields;
    }



    public void deleteFields(SudokuBoard board) {
        Random rand = new Random();
        int i = numberOfBlankFields;
        int x;
        int y;
        while (true) {
            x = rand.nextInt(0, 9);
            y = rand.nextInt(0, 9);
            if (board.get(x,y) != 0) {

                board.set(x,y,0);
                if (--i == 0) {
                    break;
                }
            }

        }
    }
}
