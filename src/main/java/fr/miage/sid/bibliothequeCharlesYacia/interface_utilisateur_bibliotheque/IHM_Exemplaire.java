package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Emprunt;
import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Oeuvre;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IHM_Exemplaire implements Initializable{
	
	private static final Logger LOG = Logger.getLogger(Gestion_Exemplaire.class.getName());
	
	private Gestion_Exemplaire gestionExemplaire = new Gestion_Exemplaire();
	
	private Gestion_Emprunt gestionEmprunt = new Gestion_Emprunt();
	
	private Gestion_Oeuvre gestionOeuvre = new Gestion_Oeuvre();
	
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
	@FXML TableColumn<Exemplaire, Date> tabDateAr;
	
	public void initialize(URL location, ResourceBundle resources){
		
		if (location.equals(getClass().getClassLoader().getResource("view/exemplaire/ExemplaireView.fxml"))) {
			tabIdEx.setCellValueFactory(new PropertyValueFactory<Exemplaire, Number>("id"));
			tabTitreEx.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("titre"));
			tabEtatEx.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("etat"));
			tabStatutEx.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("statut"));
			tabDateAr.setCellValueFactory(new PropertyValueFactory<Exemplaire, Date>("dateArchivage"));
			
		
			tabTitreEx.setCellFactory(TextFieldTableCell.<Exemplaire>forTableColumn());
			tabEtatEx.setCellFactory(TextFieldTableCell.<Exemplaire>forTableColumn());
			tabStatutEx.setCellFactory(TextFieldTableCell.<Exemplaire>forTableColumn());

			getListExemplaires();
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/exemplaire/formAddEx.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListEtat();
            getListOeuvreSelect();
            	
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/exemplaire/formUpdEx.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListExemplairesUnDeleted();
            getListEtatSelect();
            	
		}
	}
	
	private void getListExemplairesSelect() {
		select.setItems(gestionExemplaire.ListerExemplaires());
	}

	@FXML
	public void actualizeList () {
		getListExemplaires();
	}
	
	private void getListOeuvreSelect() {
		selectOeuvres.setItems(gestionExemplaire.ListerOeuvres());
	}

	private void getListEtat() {
		selectEtats.getItems().addAll( "Neuf","Bon","Moyen");
	}
	
	private void getListEtatSelect() {
		selectEtats.getItems().addAll( "Neuf","Bon","Moyen","Abimé");
	}
	
	private void getListExemplairesUnDeleted() {
		select.setItems(gestionExemplaire.ListerExemplairesUnDeleted());
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
		tabViewEx.setItems(gestionExemplaire.ListerExemplaires());
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
		try {
		  if (textFieldsValid()) {
			//get values from form
			String etat = (String) selectEtats.getSelectionModel().getSelectedItem();
			Oeuvre oeuvre = gestionExemplaire.getOeuvreByName(selectOeuvres.getSelectionModel().getSelectedItem());
				
			//save data in Gestion Exemplaire
			gestionExemplaire.ajouterExemplaire(etat, oeuvre);
			gestionOeuvre.setNbTotalDisponibles(oeuvre);
			result.setText("L'exemplaire a été ajouté !");
			result.setTextFill(Color.GREEN);
	      } else {
	        result.setText("  Veuillez remplir tout les champs !");
			result.setTextFill(Color.RED);
	       }
    	} catch(Exception e) {
			result.setText(" Impossible d'ajouter l'exemplaire ! ");
			result.setTextFill(Color.RED);
		}
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
		try {
		  if (SelectFieldsValid()) {
			//get values from form
			Exemplaire exemplaire = (Exemplaire) select.getSelectionModel().getSelectedItem();
			String etat = selectEtats.getSelectionModel().getSelectedItem().toString();
				
			//save data in Gestion Exemplaire
			gestionExemplaire.modifierExemplaire(exemplaire, etat);
			if (etat.equals("Abimé")) {
   	     	  gestionEmprunt.setStatutExemplaire(exemplaire,"Indisponible");
   	     	  gestionOeuvre.setNbIndisponibles(exemplaire.getOeuvre());
   	     	} else {
   	     	  gestionEmprunt.setStatutExemplaire(exemplaire,"Disponible");
   	     	}
			result.setText("L'exemplaire a été modifié !");
			result.setTextFill(Color.GREEN);
	        } else {
	        	result.setText("  Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
		} catch(Exception e) {
			result.setText(" Impossible de modifier l'exemplaire ! ");
			result.setTextFill(Color.RED);
		}

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
     	resultEx.setText("Veuillez sélectionner un exemplaire à supprimer !");
		resultEx.setTextFill(Color.RED);
		} else {
			Exemplaire exemplaire = tabViewEx.getSelectionModel().getSelectedItem();
			int exemplaireID = exemplaire.getId();
			if (exemplaire.getEtat().equals("Abimé") && exemplaire.getDateArchivage() == null) {
				//2
				gestionOeuvre.setNbTotalDel(exemplaire.getOeuvre());
				
			} else if  (!exemplaire.getEtat().equals("Abimé") && exemplaire.getDateArchivage() == null) {
				//4
				gestionOeuvre.setNbTotalDispoDel(exemplaire.getOeuvre());
			} else {
				//1 && 3
				System.out.println("je ne suis pas abimé et je suis  archivé");
			}
			gestionExemplaire.supprimerExemplaire(exemplaireID);
			resultEx.setText("L'exemplaire avec l'ID " + exemplaireID + " a été supprimé !");
			resultEx.setTextFill(Color.GREEN);
			getListExemplaires();
		}
	}
	
	@FXML
    public void archiverExemplaire(ActionEvent event) throws IOException, SQLException {
        if (tabViewEx.getSelectionModel().getSelectedItem() == null) {
        	resultEx.setText("Veuillez sélectionner un exemplaire à archiver !");
        	resultEx.setTextFill(Color.RED);
		} else {
			Exemplaire exemplaire = tabViewEx.getSelectionModel().getSelectedItem();
			int exemplaireID = exemplaire.getId();
			resultEx.setText("L'exemplaire avec l'ID " + exemplaireID + " a été archivé !");
			resultEx.setTextFill(Color.GREEN);
			if (exemplaire.getEtat().equals("Abimé")) {
				gestionOeuvre.setNbTotalDel(exemplaire.getOeuvre());
			} else {
				gestionOeuvre.setNbTotalDispoDel(exemplaire.getOeuvre());
				gestionEmprunt.setStatutExemplaire(exemplaire,"Indisponible");
			}
			gestionExemplaire.archiverExemplaire(exemplaireID);
			getListExemplaires();
		}
		
    };
	
	/**
	 * 
	 */
	@FXML
	public void  selectExemplaire() {
		if (select.getSelectionModel().getSelectedItem() == null) {
        	result.setText("Veuillez selectionner un exemplaire à modifier !");
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
	
	private boolean textFieldsValid() {

        boolean validTextFields = true;

        if (selectOeuvres.getSelectionModel().getSelectedItem() == null) {
            validTextFields = false;
            selectOeuvres.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        if (selectEtats.getSelectionModel().getSelectedItem() == null) {
            validTextFields = false;
            selectEtats.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        return validTextFields;
    }
	
	private boolean SelectFieldsValid() {

        boolean validTextFields = true;

        if (select.getSelectionModel().getSelectedItem() == null) {
            validTextFields = false;
            select.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        if (selectEtats.getSelectionModel().getSelectedItem() == null) {
            validTextFields = false;
            selectEtats.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        return validTextFields;
    }
	
}
