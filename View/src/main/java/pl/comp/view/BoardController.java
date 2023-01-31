package pl.comp.view;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.Dao;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuBoardDaoFactory;
import pl.comp.model.SudokuSolver;
import pl.comp.model.exceptions.BadValueException;
import pl.comp.model.exceptions.FileException;
import pl.comp.model.exceptions.JdbcException;
import pl.comp.view.exceptions.BindingException;
import pl.comp.view.exceptions.SceneLoadingException;


public class BoardController {

    private static final SafeLogger log = new SafeLogger(Javafx.class);
    @FXML
    private GridPane board;


    private Stage stage;

    private Scene scene;

    private Parent parent;


    @FXML
    private TextField textField;


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
                number.setMinHeight(41);
                number.setMaxWidth(40);
                number.setStyle("-fx-alignment: center;-fx-font-weight: bold;");
                Pane p = new Pane();

                if (((i % 3 == 2) && (j % 3 == 2)) && ( i != 8 && j != 8)) {
                    p.setStyle("-fx-border-color: transparent lightblue lightblue transparent;-fx-border-width: 4 ");
                } else if ( j == 2 || j == 5) {
                    p.setStyle("-fx-border-color: transparent lightblue transparent transparent; -fx-border-width: 4");
                } else if ( i == 2 || i == 5) {
                    p.setStyle("-fx-border-color: transparent transparent lightblue transparent;-fx-border-width: 4 ");
                }


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



//                board.add(number,j,i);
                p.getChildren().add(number);
                board.add(p,j,i);

            }
        }
    }

    @FXML
    void save() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        String name = textField.getText();

        if (!sudokuBoard1.checkBoard()) {
            BadValueException e = new BadValueException(ResourceBundle.getBundle("pl.comp.model.Exceptions")
                    .getObject("!incorrect").toString());
            exceptionWindow(e);
            return;
        }


        if (name.isBlank()) {
            JdbcException e = new JdbcException(ResourceBundle.getBundle("pl.comp.model.Exceptions")
                    .getObject("!wrong_sudoku_name2").toString());
            exceptionWindow(e);
            return;
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

        textField.setText("");
    }

    @FXML
    void read() {

        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        String name = textField.getText();
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
        textField.setText("");

    }

    @FXML
    public void check() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(ResourceBundle.getBundle("Bundle").getString("CheckResult"));
        alert.setHeaderText(ResourceBundle.getBundle("Bundle").getString("CheckResult"));

        if (!sudokuBoard1.checkBoard()) {

            alert.setContentText(ResourceBundle.getBundle("Bundle").getString("incorrect"));

        } else {
            alert.setContentText(ResourceBundle.getBundle("Bundle").getString("correct"));
        }
        alert.showAndWait();
    }

    @FXML
    public void menu(ActionEvent event) throws  SceneLoadingException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("choiceScene.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle"));
        try {
            parent = loader.load();
        } catch (IOException e) {
            throw new SceneLoadingException(e);
        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void exceptionWindow(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ResourceBundle.getBundle("Bundle").getString("Error"));
        alert.setContentText(e.getMessage());
        alert.setHeaderText(ResourceBundle.getBundle("Bundle").getString("Error"));
        alert.showAndWait();
    }

}
