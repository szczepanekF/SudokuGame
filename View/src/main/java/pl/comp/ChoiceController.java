package pl.comp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoiceController {

    private Stage stage;
    private Scene scene;
    private Parent parent;

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
    private Button trudny;

    @FXML
    void SrClicked(ActionEvent event) {

    }

    @FXML
    void Start(ActionEvent event) {
        try {
            switchToBoard(event);
        } catch (IOException e) {

        }
    }

    @FXML
    void TClicked(ActionEvent event) {

    }

    @FXML
    void lClicked(ActionEvent event) {

    }

}
