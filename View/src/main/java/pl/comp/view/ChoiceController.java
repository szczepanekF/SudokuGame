package pl.comp.view;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;


public class ChoiceController {

    private Stage stage;
    private Scene scene;
    private Parent parent;

    public static Level getLvl() {
        return lvl;
    }

    private static Level lvl = Level.EASY;

    public void switchToBoard(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getClassLoader().getResource("boardScene.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private RadioButton levelEasy;

    @FXML
    private RadioButton levelMedium;

    @FXML
    private RadioButton levelHard;


    public void setLevel(ActionEvent event) {

        if (levelEasy.isSelected()) {
            lvl = Level.EASY;
        } else if (levelMedium.isSelected()) {
            lvl = Level.MEDIUM;
        } else if (levelHard.isSelected()) {
            lvl = Level.HARD;
        }
    }


    @FXML
    void start(ActionEvent event) {
        try {
            switchToBoard(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
