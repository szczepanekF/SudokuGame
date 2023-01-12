package pl.comp.view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Dao;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuBoardDaoFactory;
import pl.comp.model.SudokuSolver;
import pl.comp.model.exceptions.FileException;


public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(Javafx.class);
    @FXML
    private GridPane board;
    @FXML
    private Button saveButton;


    private SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard sudokuBoard1 = new SudokuBoard(solver);
    private StringConverter tlumacz = new Converter();





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
                ValidatingSudokuTextField number = new ValidatingSudokuTextField();
                number.setMinHeight(40);
                number.setMaxWidth(40);
                number.setStyle("-fx-alignment: center;-fx-font-weight: bold;");



                if (sudokuBoard1.get(i,j) != 0) {
                    number.setDisable(true);
                }
                Bindings.bindBidirectional(number.textProperty(),
                        new Field(sudokuBoard1,i,j).fieldProperty(), tlumacz);


                //log.info("Filling board: field [{},{}] value = {}", i, j, sudokuBoard1.get(i,j));
                board.add(number,j,i);
            }
        }
    }

    @FXML
    void save() throws FileException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
       Dao<SudokuBoard> dao = factory.getFileDao("zapis.txt");

        try {
            dao.write(sudokuBoard1);
            log.info("Successfully saved board to a file");
        } catch (FileException e) {
            log.warn("Can't save board to a file");
            throw e;
        }

    }

    @FXML
    void read() throws FileException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> dao = factory.getFileDao("zapis.txt");
        try {
            sudokuBoard1 = dao.read();
            log.info("Successfully read board from a file");
        } catch (FileException e) {
            log.warn("Can't read board from a file");
            throw e;
        }




        board.getChildren().clear();

        fillBoard();


    }

}
