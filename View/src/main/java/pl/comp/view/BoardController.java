package pl.comp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuSolver;


public class BoardController {
    @FXML
    private GridPane board;

    private SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard sudokuBoard1 = new SudokuBoard(solver);





    @FXML
    public void initialize() {
        sudokuBoard1.solveGame();
        Level lvl = ChoiceController.getLvl();
        lvl.deleteFields(sudokuBoard1);
        fillBoard();
    }

    public void fillBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField number = new TextField();
                number.setMinHeight(40);
                number.setMaxWidth(40);
                number.setStyle("-fx-alignment: center;-fx-font-weight: bold;");
                if (sudokuBoard1.get(i,j) != 0) {
                    number.setDisable(true);
                    number.setText(Integer.toString(sudokuBoard1.get(i,j)));
                }

                board.add(number,i,j);
            }
        }
    }

}
