package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Auteur;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Auteur;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IHM_Auteur implements Initializable{
	
	/*
	 *  Attributs
	 */
	
	private static final Logger LOG = Logger.getLogger(Gestion_Auteur.class.getName());
	
	private Gestion_Auteur gestionAuteur = new Gestion_Auteur();
	
	@FXML
	private Parent root;
	
	@FXML private Button submit;
	@FXML private Button cancel;
	@FXML private Button actualize;
	
	@FXML private ComboBox select;
	
	@FXML private Label result;
	@FXML private Label resultA;
	
	@FXML private TextField lastname;
	@FXML private TextField firstname;
	@FXML private TextField searchAuteur;
	
	@FXML TableView<Auteur> tabViewA;
	@FXML TableColumn<Auteur, Number> tabIdA;
	@FXML TableColumn<Auteur, String> tabNameA;
	@FXML TableColumn<Auteur, String> tabFNameA;
	@FXML TableColumn<Auteur, ArrayList> tabO;
	@FXML TableColumn<Auteur, Date> tabDateS;
	
	@Override
   	public void initialize(URL location, ResourceBundle resources){
	
		if (location.equals(getClass().getClassLoader().getResource("view/auteur/AuteurView.fxml"))) {
			tabIdA.setCellValueFactory(new PropertyValueFactory<Auteur, Number>("id"));
			tabNameA.setCellValueFactory(new PropertyValueFactory<Auteur, String>("nom"));
			tabFNameA.setCellValueFactory(new PropertyValueFactory<Auteur, String>("prenom"));
			tabO.setCellValueFactory(new PropertyValueFactory<Auteur, ArrayList>("livres"));
			tabDateS.setCellValueFactory(new PropertyValueFactory<Auteur, Date>("dateSuppression"));
		
			tabNameA.setCellFactory(TextFieldTableCell.<Auteur>forTableColumn());
			tabFNameA.setCellFactory(TextFieldTableCell.<Auteur>forTableColumn());
			
			resultA.setText("Aucune action effectuée !");
			resultA.setTextFill(Color.BLUE);
			
			getListAuteurs();
		}
			
		if (location.equals(getClass().getClassLoader().getResource("view/auteur/formUpdA.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListAuteursSelect();
            	
		}
		
	}
	
	@FXML
    private void closeView(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }    
	
	/*
	 * List all autors in tableView
	 */
	
	public void getListAuteurs()
	{
		tabViewA.setItems(gestionAuteur.ListerAuteurs());
	}
	
	/*
	 * Get the list of autors inside select for modForm
	 */
	
	public void getListAuteursSelect()
	{
		select.setItems(gestionAuteur.ListerAuteursUnDeleted());
	}
	
	/*
	 * Find a usager by lastname/firstname research
	 */
	
	@FXML
	public void trouverAuteur () {
		ObservableList<Auteur> list = gestionAuteur.trouverAuteur(searchAuteur.getText());
		LOG.fine(searchAuteur.getText());
		tabViewA.setItems(list);
	}
	
	@FXML
	public void selectAuteur() {
		if (select.getSelectionModel().getSelectedItem() == null) {
        	result.setText("Veuillez selectionner un auteur à modifier avant !");
			result.setTextFill(Color.RED);
		} else {
			Auteur auteur = (Auteur) select.getSelectionModel().getSelectedItem();
			int usagerID = auteur.getId();
			//auto complement fields
			Auteur modauteur = gestionAuteur.trouverAuteur(usagerID);
			lastname.setText(modauteur.getNom());
			firstname.setText(modauteur.getPrenom());
		}
	}
	
	@FXML
	public void actualizeList () {
		getListAuteurs();
	}
	
	@FXML
	public void ajoutFormA(ActionEvent event) {
        try {
        	//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/formAddU.fxml"));
        	//Parent part = fxmlLoader.load();
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/auteur/formAddA.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel Auteur");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
                       
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    @FXML
	private void ajouterAuteur(ActionEvent event) {
    	if (! textFieldsValid()) {
            // one or more of the text fields are empty
    		result.setText("Veuillez remplir les champs manquants !");
    		result.setTextFill(Color.RED);
            return;
        }
		LOG.fine(lastname.getText() + ", " + firstname.getText());
		String nom = lastname.getText();
		String prenom = 	firstname.getText();
		
		//save data in Gestion Back
		gestionAuteur.ajouterAuteur(nom,prenom);
		result.setText("L'auteur a été ajouté !");
		result.setTextFill(Color.GREEN);
	}

    @FXML
    public void modFormA(ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/auteur/formUpdA.fxml"));
			root = loader.load();
			IHM_Auteur controller = loader.getController();
	        Stage stage = new Stage();
	        stage.setTitle("Modifier un auteur");
	        stage.setScene(new Scene(root));  
	        stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    };

	@FXML
	private void modifierAuteur(ActionEvent event) {
		if (! textFieldsValid()) {
            // one or more of the text fields are empty
    		result.setText("Veuillez remplir les champs manquants !");
    		result.setTextFill(Color.RED);
    		return;
        }
		System.out.println(lastname.getText() + ", " + firstname.getText());
		Auteur us = (Auteur) select.getSelectionModel().getSelectedItem();
		int id = us.getId();
		String nom = lastname.getText();
		String prenom = 	firstname.getText();
		
		//save data in Gestion Back
		gestionAuteur.modifierAuteur(id,nom,prenom);
		result.setText("L'auteur a été modifié !");
		result.setTextFill(Color.GREEN);
	}
    
    @FXML
    public void supprimerAuteur(ActionEvent event) throws IOException, SQLException {
        if (tabViewA.getSelectionModel().getSelectedItem() == null) {
        	resultA.setText("Veuillez sélectionner un auteur à supprimer avant !");
			resultA.setTextFill(Color.RED);
		} else {
			Auteur auteur = tabViewA.getSelectionModel().getSelectedItem();
			int auteurID = auteur.getId();
			resultA.setText("L'auteur avec l'ID " + auteurID + " a été supprimé !");
			resultA.setTextFill(Color.GREEN);
			gestionAuteur.supprimerAuteur(auteurID);
			getListAuteurs();
		}
		
    };
    
    @FXML
    public void archiverAuteur(ActionEvent event) throws IOException, SQLException {
        if (tabViewA.getSelectionModel().getSelectedItem() == null) {
        	resultA.setText("Veuillez sélectionner un usager à archiver avant !");
			resultA.setTextFill(Color.RED);
		} else {
			Auteur auteur = tabViewA.getSelectionModel().getSelectedItem();
			int auteurID = auteur.getId();
			resultA.setText("L'usager avec l'ID " + auteurID + " a été supprimé !");
			resultA.setTextFill(Color.GREEN);
			gestionAuteur.archiverAuteur(auteurID);
			getListAuteurs();
		}
		
    };
    
    private boolean textFieldsValid() {

        boolean validTextFields = true;

        if (lastname.getText().isEmpty()) {
            validTextFields = false;
            lastname.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        if (firstname.getText().isEmpty()) {
            validTextFields = false;
            firstname.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        return validTextFields;
    }
    
}
