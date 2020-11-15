package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHM_Front {
	
	@FXML
	private Parent root;
	
	@FXML
   	public void initialize() {
		
	}
	
	/*
	 *  Add Methods
	 */
    
    public void reserverOeuvre(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formAddRes.fxml"));
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
    
    public void empruntExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formAddE.fxml"));
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
    
    /*
	 *  Update Methods
	 */
    
    public void modifierReservation(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formUpdRes.fxml"));
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
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formDelRes.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Annuler une réservation");
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
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formDelE.fxml"));
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
    

	

	public void emprunter(String nom, String titre) {
		throw new UnsupportedOperationException();
	}

	public void reserver(String nom, String titre) {
		throw new UnsupportedOperationException();
	}

	public void annulerReservation(String nom, String titre) {
		throw new UnsupportedOperationException();
	}

	public void rendreExemplaire(String nom, String titre, int numero) {
		throw new UnsupportedOperationException();
	}
}
