package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Back;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IHM_Back {
	
	/*
	 *  Attributs
	 */
	
	private static final Logger LOG = Logger.getLogger(Gestion_Back.class.getName());
	
	private Gestion_Back gb = new Gestion_Back();
	
	@FXML
	private Parent root;
	
	@FXML
    private Button submit;
	
	@FXML
    private Button cancel;
	
	@FXML
    private Label result;
	
	@FXML
    private TextField lastname;
	
	@FXML
    private TextField firstname;
	
	@FXML
    private DatePicker dateB;
	
	@FXML
    private TextField adress;
	
	@FXML
    private TextField city;
	
	@FXML
    private TextField cp;
	
	@FXML
    private TextField mail;
	
	@FXML
    private TextField tel;
	
	@FXML
   	public void initialize() {
		
	}
	
	/*
	 *  Add Methods
	 */
	
	@FXML
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
    
    @FXML
	private void getInfosU(ActionEvent event) {

    	result.setText("L'usager a été ajouté");
		result.setTextFill(Color.GREEN);
		LOG.severe(lastname.getText() + ", " + firstname.getText() + ", " + dateB.getValue() + ", " + adress.getText() + ", " + cp.getText() + ", " + city.getText()
		+ ", " + mail.getText() + ", " + tel.getText());
		String LastName = lastname.getText();
		String FirstName = 	firstname.getText();
		java.sql.Date dateBirth = java.sql.Date.valueOf(dateB.getValue());
		String Adress = 	adress.getText();
		int Cp = 	Integer.parseInt(cp.getText());
		String City = 	city.getText();
		String Mail = 	mail.getText();
		String Tel = 	tel.getText();
		
		//save data in Gestion Back
//      Usager usager= new Usager("Yacia","Adel", "2Rue ludovic beauchet", 54000, "Nancy", "0768548385", "adel.yacia@gmail.com", new Date());
		gb.ajouterUsager(LastName,FirstName, Adress,Cp, City, Tel, Mail , dateBirth);
	}
    
    @FXML
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
    
    @FXML
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
    
    @FXML
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
    
    @FXML
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
    
    @FXML
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
    
    @FXML
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
    
    @FXML
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
