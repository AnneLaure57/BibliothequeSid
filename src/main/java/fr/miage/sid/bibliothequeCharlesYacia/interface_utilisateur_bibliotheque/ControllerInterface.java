package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Back;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import javafx.collections.ObservableList;
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

public class ControllerInterface {
	
	/*
	 *  Attributs
	 */
	
	private static final Logger LOG = Logger.getLogger(Gestion_Back.class.getName());
	
	private Gestion_Back gestionBack = new Gestion_Back();
	private IHM_Back ihmBack = new IHM_Back();
	
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
	
	@FXML private TextField searchUsager;
	
	@FXML
   	public void initialize() {
		
		//result.setText("Aucune recherche effectuée !");
	}
	
	
	@FXML
	public void ajoutFormU(ActionEvent event) {
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
	private void ajouterUsager(ActionEvent event) {

    	result.setText("L'usager a été ajouté");
		result.setTextFill(Color.GREEN);
		LOG.severe(lastname.getText() + ", " + firstname.getText() + ", " + dateB.getValue() + ", " + adress.getText() + ", " + cp.getText() + ", " + city.getText()
		+ ", " + mail.getText() + ", " + tel.getText());
		String nom = lastname.getText();
		String prenom = 	firstname.getText();
		java.sql.Date dateNaissance = java.sql.Date.valueOf(dateB.getValue());
		String adresse = 	adress.getText();
		int codePostal = 	Integer.parseInt(cp.getText());
		String ville = 	city.getText();
		String email = 	mail.getText();
		String telephone = 	tel.getText();
		
		//save data in Gestion Back
//      Usager usager= new Usager("Yacia","Adel", "2Rue ludovic beauchet", 54000, "Nancy", "0768548385", "adel.yacia@gmail.com", new Date());
		gestionBack.ajouterUsager(nom,prenom, adresse,codePostal, ville, telephone, email , dateNaissance);
		//Pour actualiser mais cause une erreur TODO
		//ihmBack.getListUsagers();
	}
	
//	@FXML
//	private void getInfosU(ActionEvent event) {
//
//        	result.setText("L'usager a été ajouté");
//    		result.setTextFill(Color.GREEN);
//    		LOG.severe(lastname.getText() + ", " + firstname.getText() + ", " + dateB.getValue() + ", " + adress.getText() + ", " + cp.getText() + ", " + city.getText()
//    		+ ", " + mail.getText() + ", " + tel.getText());
//		
//	}

}
