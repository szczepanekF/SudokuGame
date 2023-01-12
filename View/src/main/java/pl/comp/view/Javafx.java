package pl.comp.view;


import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.view.exceptions.SceneLoadingException;

public class Javafx extends Application {

    private static final Logger log = LoggerFactory.getLogger(Javafx.class);

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
        //        Locale l = new Locale("en");
        //        Locale.setDefault(l);
        ResourceBundle rd
                = ResourceBundle
                .getBundle("pl.comp.view.Authors");
        System.out.println("English Version:");
        System.out.println("String for Title key: "
                + rd.getString("2"));
        System.out.println("String for Title key: "
                + rd.getString("1"));

        log.info("Launching app");
       launch();
    }
}