package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Back;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ControllerInterface {
	
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
		
		result.setText("Aucune recherche effectuée !");
	}
	
	@FXML
	private void getInfosU(ActionEvent event) {

        	result.setText("L'usager a été ajouté");
    		result.setTextFill(Color.GREEN);
    		LOG.severe(lastname.getText() + ", " + firstname.getText() + ", " + dateB.getValue() + ", " + adress.getText() + ", " + cp.getText() + ", " + city.getText()
    		+ ", " + mail.getText() + ", " + tel.getText());
		
	}

}
