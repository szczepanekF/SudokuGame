package pl.comp.view;

import javafx.util.StringConverter;

public class Converter extends StringConverter<Integer> {



    @Override
    public String toString(Integer i) {
        return i.toString();
    }

    @Override
    public Integer fromString(String s) {
        if (s.matches("\\d")) {
            return Integer.parseInt(s);
        } else if (s.isBlank()){
            return 0;
        } else {
            return -1;
        }
    }
}
