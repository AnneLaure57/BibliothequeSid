package fr.miage.sid.bibliothequeCharlesYacia;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App  extends Application {

    /*@Override
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
    }*/
	
	public void start(Stage primaryStage) {
		Parent root =  null;
		try {
			//If files in resources
			root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
			//If in packages in src/main/java
			//root = FXMLLoader.load(getClass().getResource("views/Main.fxml"));
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			Platform.exit();
            System.exit(0);
		}
		Scene scene = new Scene(root);
		primaryStage.setTitle("Projet -Bibliothèque ");
		//primaryStage.getIcons().add(new Image("images/logo.jpg"));
		//scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
		//not allow to modify the window 
		//primaryStage.setResizable(false);
		//To change the visibility to false
		primaryStage.hide();
		//for the responsive window's
		primaryStage.sizeToScene();
		scene.setRoot((Parent) root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		//Démarrer le menu
		launch(args);
	}

}
