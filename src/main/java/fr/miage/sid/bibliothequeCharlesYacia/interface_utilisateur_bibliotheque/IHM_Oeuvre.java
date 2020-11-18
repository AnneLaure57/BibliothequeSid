package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Oeuvre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHM_Oeuvre implements Initializable{
	
private static final Logger LOG = Logger.getLogger(Gestion_Oeuvre.class.getName());
	
	public void initialize(URL location, ResourceBundle resources){
		
	}
	
	@FXML
    public void ajouterOeuvre(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/oeuvre/formAddO.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter une nouvelle oeuvre");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    

    @FXML
    public void supprimerOeuvre(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/oeuvre/formDelO.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Supprimer une oeuvre");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };

}
