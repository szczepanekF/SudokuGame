package pl.comp.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pl.comp.model.SudokuBoard;

public class Field {

    private static final SafeLogger log = new SafeLogger(Javafx.class);
    private IntegerProperty field;
    private SudokuBoard board;
    private int cordX;
    private int cordY;

    public Field(SudokuBoard board, int cordX, int cordY) {
        this.board = board;
        this.cordX = cordX;
        this.cordY = cordY;
        this.field = new SimpleIntegerProperty(this,"field",0);
        field.set(board.get(cordX, cordY));
        field.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number>
                                        observableValue, Number number, Number t1) {
                if (t1.intValue() <= 9 && t1.intValue() >= 0) {
                    log.info("Field [{},{}] value set to {}", cordX, cordY, t1.intValue());
                    setField(t1.intValue());
                }
            }
        });
    }

    public int getField() {
        return field.get();
    }

    public IntegerProperty fieldProperty() {
        return field;
    }

    public void setField(int fieldValue) {
        board.set(cordX, cordY,fieldValue);

    }

}
