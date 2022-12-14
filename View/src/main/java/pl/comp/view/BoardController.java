package pl.comp.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuSolver;


public class BoardController {
    @FXML
    private GridPane board;

    private SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard SudokuBoard1 = new SudokuBoard(solver);

    private SudokuBoard SudokuBoard2;

    private Level lvl;


    @FXML
    private void initialize() {
        SudokuBoard1.solveGame();
        lvl = ChoiceController.getLvl();

        lvl.deleteFields(SudokuBoard1);
        fillBoard();
    }

    public void fillBoard(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField number = new TextField();
                number.setMinSize(40,40);
                number.setStyle("-fx-alignment: center;-fx-font-weight: bold;");

                number.onInputMethodTextChangedProperty();
                if (SudokuBoard1.get(i,j) != 0) {
                    number.setDisable(true);
                    number.setText(Integer.toString(SudokuBoard1.get(i,j)));
                }

                board.add(number,i,j);
            }
        }
    }


    @FXML
    private Button check;

    @FXML
    void check(ActionEvent event) {


    }

}
