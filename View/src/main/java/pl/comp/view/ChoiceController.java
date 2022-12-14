package pl.comp.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoiceController {

    private Stage stage;
    private Scene scene;
    private Parent parent;

    public static Level getLvl() {
        return lvl;
    }

    private static Level lvl;

    public void switchToBoard(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getClassLoader().getResource("boardScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button Begin;

    @FXML
    private Button l;

    @FXML
    private Button sr;
    @FXML
    private ComboBox choice;
    @FXML
    private Button trudny;

    @FXML
    void Start(ActionEvent event) {
        if (lvl == null) {
            lvl = Level.EASY;
        }
        try {
            switchToBoard(event);
        } catch (IOException e) {

        }
    }



    @FXML
    void lClicked(ActionEvent event) {
        lvl = Level.EASY;
    }

    @FXML
    void SrClicked(ActionEvent event) {
        lvl = Level.MEDIUM;
    }

    @FXML
    void TClicked(ActionEvent event) {
        lvl = Level.HARD;
    }
}
