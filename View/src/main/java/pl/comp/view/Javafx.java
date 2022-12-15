package pl.comp.view;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Javafx extends Application {
    @Override
    public void start(Stage stage) throws IOException  {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getClassLoader().getResource("choiceScene.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}