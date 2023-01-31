package pl.comp.model;


import java.util.ListResourceBundle;

class Exceptions_en extends ListResourceBundle {
    public Exceptions_en() {
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][]  {
                {"!wrong_index", "Rows and Columns indexes are 0-8",},
                {"!wrong_value", "Values are from 0-9",},
                {"!wrong_sudoku_name", "Sudoku with specified name is missing.",},
                {"!wrong_sudoku_name2", "Sudoku name can't be blank.",},
                {"!binding_error", "Error during binding properties.",},
                {"!incorrect", "Sudoku is incorrectly solved.",},
        };
    }
}
