package pl.comp.view;


import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.comp.view.exceptions.SceneLoadingException;

public class Javafx extends Application {

    private static final SafeLogger log = new SafeLogger(Javafx.class);

    @Override
    public void start(Stage stage) throws SceneLoadingException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("choiceScene.fxml"));
        loader.setResources(ResourceBundle.getBundle("Bundle"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new SceneLoadingException(e);
        }

    }


    public static void main(String[] args) {
        log.info("Launching app");
        launch();
    }
}