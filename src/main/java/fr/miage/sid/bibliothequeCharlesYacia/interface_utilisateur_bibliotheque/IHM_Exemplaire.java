package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Usager;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
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
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IHM_Exemplaire implements Initializable{
	
	private static final Logger LOG = Logger.getLogger(Gestion_Exemplaire.class.getName());
	
	private Gestion_Exemplaire gestionExemplaire = new Gestion_Exemplaire();
	
	@FXML
	private Parent root;
	
	@FXML private ComboBox selectOeuvres;
	@FXML private ComboBox selectEtats;
	@FXML private ComboBox select;
	
	@FXML private Button submit;
	@FXML private Button cancel;
	
	@FXML private TextField titre;
	@FXML private TextField searchExemplaire;
	
	@FXML private Label result;
	@FXML private Label resultEx;
	
	@FXML TableView<Exemplaire> tabViewEx;
	@FXML TableColumn<Exemplaire, Number> tabIdEx;
	@FXML TableColumn<Exemplaire, String> tabTitreEx;
	@FXML TableColumn<Exemplaire, String> tabEtatEx;
	@FXML TableColumn<Exemplaire, String> tabStatutEx;
	
	public void initialize(URL location, ResourceBundle resources){
		
		if (location.equals(getClass().getClassLoader().getResource("view/exemplaire/ExemplaireView.fxml"))) {
			tabIdEx.setCellValueFactory(new PropertyValueFactory<Exemplaire, Number>("id"));
			tabTitreEx.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("titre"));
			tabEtatEx.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("etat"));
			tabStatutEx.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("statut"));
			
		
			tabTitreEx.setCellFactory(TextFieldTableCell.<Exemplaire>forTableColumn());
			tabEtatEx.setCellFactory(TextFieldTableCell.<Exemplaire>forTableColumn());
			tabStatutEx.setCellFactory(TextFieldTableCell.<Exemplaire>forTableColumn());

			getListExemplaires();
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/exemplaire/formAddEx.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListEtatSelect();
            getListOeuvreSelect();
            	
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/exemplaire/formUpdEx.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListExemplairesSelect();
            getListEtatSelect();
            	
		}
	}
	
	private void getListExemplairesSelect() {
		// TODO Auto-generated method stub
		select.setItems(gestionExemplaire.ListerExemplaire());
	}

	@FXML
	public void actualizeList () {
		getListExemplaires();
	}
	
	private void getListOeuvreSelect() {
		selectOeuvres.setItems(gestionExemplaire.ListerOeuvre());
	}

	private void getListEtatSelect() {
		selectEtats.getItems().addAll(
			    "Neuf",
			    "Bon",
			    "Abimé"
			);
	}
	
	/*
	 * Search by title
	 * 
	 */
	
	@FXML
	public void trouverExemplaire () {
		ObservableList<Exemplaire> list = gestionExemplaire.trouverOeuvre(searchExemplaire.getText());
		LOG.fine(searchExemplaire.getText());
		tabViewEx.setItems(list);
	}
	

	@FXML
    private void closeView(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }    
	
	private void getListExemplaires() {
		// TODO Auto-generated method stub
		tabViewEx.setItems(gestionExemplaire.ListerExemplaire());
	}

	@FXML
	public void formAddExemplaire(ActionEvent event) {
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

    }
    
    @FXML
	private void ajouterExemplaire(ActionEvent event) {
    	
		String etat = (String) selectEtats.getSelectionModel().getSelectedItem();
		
		Oeuvre oeuvre = gestionExemplaire.getOeuvreByName(selectOeuvres.getSelectionModel().getSelectedItem());
		
		//save data in Gestion Back
		gestionExemplaire.ajouterExemplaire(etat, oeuvre);
		result.setText("L'usager a été ajouté !");
		result.setTextFill(Color.GREEN);
	}
	
	@FXML
    public void formUpExemplaire(ActionEvent event) {
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
    }
	
	@FXML
	private void modifierExemplaire(ActionEvent event) {
    	
		Exemplaire exemplaire = (Exemplaire) select.getSelectionModel().getSelectedItem();
		String etat = selectEtats.getSelectionModel().getSelectedItem().toString();
		
		//save data in Gestion Back
		gestionExemplaire.modiferExemplaire(exemplaire, etat);

		result.setText("L'usager a été modifié !");
		result.setTextFill(Color.GREEN);

	}
	
	@FXML
    public void formDltExemplaire(ActionEvent event) {
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
    }
	
	@FXML 
	public void supprimerExemplaire(ActionEvent event) throws IOException, SQLException {
	 if (tabViewEx.getSelectionModel().getSelectedItem() == null) {
     	resultEx.setText("Veuillez sélectionner un exemplaire à supprimer avant !");
			resultEx.setTextFill(Color.RED);
		} else {
			Exemplaire exemplaire = tabViewEx.getSelectionModel().getSelectedItem();
			int exemplaireID = exemplaire.getId();
			gestionExemplaire.supprimerExemplaire(exemplaireID);
			resultEx.setText("L'exemplaire avec l'ID " + exemplaireID + " a été supprimé !");
			resultEx.setTextFill(Color.GREEN);
			getListExemplaires();
		}
	}
	/**
	 * 
	 */
	@FXML
	public void  selectExemplaire() {
		
			if (select.getSelectionModel().getSelectedItem() == null) {
	        	result.setText("Veuillez selectionner un exemplaire à modifier avant !");
				result.setTextFill(Color.RED);
			} else {
				Exemplaire exemplaire = (Exemplaire) select.getSelectionModel().getSelectedItem();
				int exemplaireID = exemplaire.getId();
				//auto complement fields
				Exemplaire modExemplaire = gestionExemplaire.trouverExemplaire(exemplaireID);
				
				titre.setText(modExemplaire.getTitre());
				selectEtats.getSelectionModel().select(modExemplaire.getEtat());
			}
		}
    
	
    @FXML
	public void selectOeuvre() {
			
    }
   
    
    @FXML
	public void selectEtat() {
    	
	}
}
