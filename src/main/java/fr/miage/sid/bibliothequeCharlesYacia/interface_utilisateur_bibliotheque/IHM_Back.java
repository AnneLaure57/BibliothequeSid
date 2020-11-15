package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHM_Back {
	
	@FXML
	private Parent root;
	
	@FXML
   	public void initialize() {
		
	}
	
	/*
	 *  Add Methods
	 */
	
	public void ajouterUsager(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formAddU.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel Usager");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    public void ajouterExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formAddEx.fxml"));
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
    
    public void ajouterOeuvre(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formAddO.fxml"));
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
    
    /*
	 *  Update Methods
	 */
    
    public void modifierExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formUpdEx.fxml"));
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
    
    public void modifierUsager(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formUpdU.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modifier un usager");
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
    public void supprimerUsager(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formDelU.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Supprimer un usager");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    

    public void supprimerExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formDelEx.fxml"));
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
    
    public void supprimerOeuvre(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formDelO.fxml"));
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

	public void ajouterExemplaire(Oeuvre oeuvre, String titre) {
		throw new UnsupportedOperationException();
	}

	public void ajouterUsager(String nom) {
		
		throw new UnsupportedOperationException();
	}

	public void ajouterOeuvre(String titre) {
		throw new UnsupportedOperationException();
	}

	public void supprimerExemplaire(Oeuvre oeuvre) {
		throw new UnsupportedOperationException();
	}

	public void supprimerOeuvre(String titre) {
		throw new UnsupportedOperationException();
	}

	public void supprimerUsager(String nom) {
		throw new UnsupportedOperationException();
	}

	public void modifierUsager(Usager usager) {
		throw new UnsupportedOperationException();
	}

	public void modifierExemplaire(Exemplaire exemplaire) {
		throw new UnsupportedOperationException();
	}
}
