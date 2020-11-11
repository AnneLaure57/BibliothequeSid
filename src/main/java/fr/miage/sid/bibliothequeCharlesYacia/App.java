package fr.miage.sid.bibliothequeCharlesYacia;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
         final String javaVersion = System.getProperty("java.version");
         final String javafxVersion = System.getProperty("javafx.version");
         final Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
         final Scene scene = new Scene(new StackPane(l), 640, 480);
         primaryStage.setScene(scene);
         primaryStage.show();
     }

    public static void main(String[] args) {
        launch();
    }

}
