package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Exemplaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHM_Exemplaire implements Initializable{
	
	private static final Logger LOG = Logger.getLogger(Gestion_Exemplaire.class.getName());
	
	public void initialize(URL location, ResourceBundle resources){
		
	}
	
	@FXML
	public void ajouterExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/exemplaire/formAddEx.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel exemplaire");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
	
	@FXML
    public void modifierExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/exemplaire/formUpdEx.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modifier un exemplaire");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
	
	@FXML
    public void supprimerExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/exemplaire/formDelEx.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Supprimer un exemplaire");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
}
