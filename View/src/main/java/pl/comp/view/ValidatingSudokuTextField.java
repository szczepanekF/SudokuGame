package pl.comp.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ValidatingSudokuTextField extends TextField {

    ValidatingSudokuTextField() {
        textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String>
                                        observableValue, String s, String t1) {
                if (!(t1.matches("[1-9]") || t1.isBlank())) {
                    setText(s);
                }
        }
        });
    }


}
