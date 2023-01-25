package pl.comp.view;

import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Dao;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuBoardDaoFactory;
import pl.comp.model.SudokuSolver;
import pl.comp.model.exceptions.FileException;
import pl.comp.model.exceptions.JdbcException;
import pl.comp.view.exceptions.BindingException;


public class BoardController {

    private static final SafeLogger log = new SafeLogger(Javafx.class);
    @FXML
    private GridPane board;
    @FXML
    private Button saveButton;

    @FXML
    private TextField readField;
    @FXML
    private TextField writeField;


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



                try {
                    Bindings.bindBidirectional(number.textProperty(),JavaBeanIntegerPropertyBuilder.create()
                            .bean(new Field(sudokuBoard1, i,j))
                            .name("Field")
                            .build(),tlumacz);
                }
                catch (NoSuchMethodException e){
                    log.error("Cannot build required property");
                    exceptionWindow(new BindingException(ResourceBundle.getBundle(
                            "pl.comp.model.Exceptions")
                            .getObject("!binding_error").toString()));
                }



                board.add(number,j,i);
            }
        }
    }

    @FXML
    void save() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        String name = readField.getText();
        if (name.isBlank()) {
            JdbcException e = new JdbcException(ResourceBundle.getBundle("pl.comp.model.Exceptions")
                    .getObject("!wrong_sudoku_name2").toString());
            exceptionWindow(e);
        }

        try (Dao<SudokuBoard> dao = factory.getJdbcDao(name)) {
            dao.write(sudokuBoard1);
            log.info("Successfully saved board to a file");
        } catch (JdbcException | FileException e) {
            log.warn("Can't save board to a file");
            exceptionWindow(e);
        } catch (Exception e) {
           exceptionWindow(new JdbcException(e));
        }
    }

    @FXML
    void read() {

        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        String name = writeField.getText();
        if (name.isBlank()) {
            JdbcException exc = new JdbcException(ResourceBundle.getBundle(
                    "pl.comp.model.Exceptions").getObject("!wrong_sudoku_name2").toString());
            exceptionWindow(exc);
            return;

        }

        try (Dao<SudokuBoard> dao = factory.getJdbcDao(name)) {
            sudokuBoard1 = dao.read();
            log.info("Successfully read board from a file");
        } catch (JdbcException e) {
            log.warn("Can't read board from a file");
            exceptionWindow(e);
        } catch (Exception e) {
            exceptionWindow(new JdbcException(e));
        }


        board.getChildren().clear();

        fillBoard();


    }

    public void exceptionWindow(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ResourceBundle.getBundle("Bundle").getString("Error"));
        alert.setContentText(e.getMessage());
        alert.setHeaderText(ResourceBundle.getBundle("Bundle").getString("Error"));
        alert.showAndWait();
    }

}
