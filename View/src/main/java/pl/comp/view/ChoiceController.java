package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import pl.comp.view.exceptions.SceneLoadingException;


public class ChoiceController {


    private static final SafeLogger log = new SafeLogger(Javafx.class);
    private Stage stage;
    private Scene scene;
    private Parent parent;

    public static Level getLvl() {
        return lvl;
    }

    private static Level lvl = Level.EASY;
    private ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
    private ResourceBundle authors = ResourceBundle.getBundle("pl.comp.view.Authors");

    public void switchToBoard(ActionEvent event) throws SceneLoadingException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("boardScene.fxml"));
        loader.setResources(bundle);
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

    @FXML
    private RadioButton levelEasy;

    @FXML
    private RadioButton levelMedium;

    @FXML
    private RadioButton levelHard;


    public void setLevel(ActionEvent event) {

        if (levelEasy.isSelected()) {
            lvl = Level.EASY;
            log.info("Level switched to easy");
        } else if (levelMedium.isSelected()) {
            lvl = Level.MEDIUM;
            log.info("Level switched to medium");
        } else if (levelHard.isSelected()) {
            lvl = Level.HARD;
            log.info("Level switched to hard");
        }

    }


    @FXML
    void start(ActionEvent event) throws SceneLoadingException {
        try {
            switchToBoard(event);
            log.info("Start game");
        } catch (IOException e) {
            log.error("Bad game settings!");
            throw new SceneLoadingException(e);
        }
    }

    @FXML
    void setToEN(ActionEvent event) throws SceneLoadingException {
        Locale l = new Locale("EN");
        Locale.setDefault(new Locale("EN"));
        try {
            reloadLen(l);
        } catch (SceneLoadingException e) {
            throw new SceneLoadingException(e);
        }

        log.info("Language changed to EN");
    }

    @FXML
    void setToPL(ActionEvent event) throws SceneLoadingException {
        Locale l = new Locale("PL");
        Locale.setDefault(new Locale("PL"));
        try {
            reloadLen(l);
            log.info("Language changed to PL");
        } catch (SceneLoadingException e) {
            throw new SceneLoadingException(e);
        }


    }

    @FXML
    void authors(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("InfoTitle"));
        alert.setContentText(authors.getString("1") + ", " + authors.getString("2"));
        alert.setHeaderText(bundle.getString("InfoText"));
        alert.showAndWait();
    }


    public void reloadLen(Locale l) throws SceneLoadingException {
        FXMLLoader loader = new FXMLLoader();
        bundle = ResourceBundle.getBundle("Bundle",l);
        loader.setLocation(getClass().getClassLoader().getResource("choiceScene.fxml"));
        loader.setResources(bundle);
        try {
            parent = loader.load();
            stage = (Stage) (levelHard.getScene().getWindow());
            scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new SceneLoadingException();
        }
    }

}
