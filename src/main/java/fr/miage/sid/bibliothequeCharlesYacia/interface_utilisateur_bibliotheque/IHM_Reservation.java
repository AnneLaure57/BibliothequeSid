package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHM_Reservation implements Initializable{
	
	@FXML
	private Parent root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	};
    
	
	/*
	 *  Add Methods
	 */
    
    public void reserverOeuvre(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/reservation/formAddRes.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter une nouvelle réservation");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
   
    /*
	 *  Update Methods
	 */
    
    public void modifierReservation(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/reservation/formUpdRes.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modifier Reservation");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    /*
	 *  Delete Methods
	 */

    public void annulerReservation(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/reservation/formDelRes.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Annuler une réservation");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
