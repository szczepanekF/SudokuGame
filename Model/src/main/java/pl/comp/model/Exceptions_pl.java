package pl.comp.model;


import java.util.ListResourceBundle;

class Exceptions_pl extends ListResourceBundle {
    public Exceptions_pl() {
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][]  {
                {"!wrong_index", "Indexy kolumn i rzędów przyjmują wartości 0-8",},
                {"!wrong_value", "Wartości przyjmują wartości 0-9",},
                {"!wrong_sudoku_name", "Brak sudoku o takiej nazwie.",},
                {"!wrong_sudoku_name2", "Nazwa sudoku nie moze byc pusta.",},
                {"!binding_error", "Błąd podczas łączenia właściwości.",},
        };
    }
}
