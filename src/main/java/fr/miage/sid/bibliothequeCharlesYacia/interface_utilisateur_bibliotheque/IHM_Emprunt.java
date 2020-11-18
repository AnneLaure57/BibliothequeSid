package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Emprunt;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHM_Emprunt implements Initializable{
	
	private static final Logger LOG = Logger.getLogger(Gestion_Emprunt.class.getName());
	
	public void initialize(URL location, ResourceBundle resources){
		
	}
	
	public void empruntExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/emprunt/formAddE.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel emprunt");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    
    public void rendreExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/emprunt/formDelE.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Rendre une exemplaire");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
}