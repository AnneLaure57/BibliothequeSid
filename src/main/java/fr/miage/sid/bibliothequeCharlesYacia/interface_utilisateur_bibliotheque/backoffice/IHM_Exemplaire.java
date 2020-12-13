package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque.backoffice;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.backoffice.Gestion_Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.backoffice.Gestion_Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.frontoffice.Gestion_Emprunt;
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
import javafx.scene.image.Image;
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
			
			resultEx.setText("aucune action effectuée !");
			resultEx.setTextFill(Color.BLUE);
			

			getListExemplars();
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/exemplaire/formAddEx.fxml"))) {
			
            result.setText("aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListStates();
            getListArtworksSelect();
            	
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/exemplaire/formUpdEx.fxml"))) {
			
            result.setText("aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListExemplarsUnDeleted();
            getListStatesSelect();
            	
		}
	}
	
	private void getListExemplarsSelect() {
		select.setItems(gestionExemplaire.listerExemplaires());
	}

	@FXML
	public void actualiserListe () {
		getListExemplars();
		resultEx.setText("actualisation terminée !");
		resultEx.setTextFill(Color.BLUE);
	}
	
	private void getListArtworksSelect() {
		selectOeuvres.setItems(gestionOeuvre.listerOeuvresDispo());
	}

	private void getListStates() {
		selectEtats.getItems().addAll( "Neuf","Bon","Moyen");
	}
	
	private void getListStatesSelect() {
		selectEtats.getItems().addAll( "Neuf","Bon","Moyen","Abimé");
	}
	
	private void getListExemplarsUnDeleted() {
		select.setItems(gestionExemplaire.listerExemplairesDispo());
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
		resultEx.setText("nombres d'éléments trouvé(s) : " + list.size());
		resultEx.setTextFill(Color.BLUE);
	}
	

	@FXML
    private void fermerVue(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }    
	
	private void getListExemplars() {
		tabViewEx.setItems(gestionExemplaire.listerExemplaires());
	}

	@FXML
	public void ajoutFormEx(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/exemplaire/formAddEx.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel exemplaire");
            stage.getIcons().add(new Image("images/open-book.png"));
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
		  if (verifierChamps()) {
			//get values from form
			String etat = (String) selectEtats.getSelectionModel().getSelectedItem();
			Oeuvre oeuvre = gestionExemplaire.getOeuvreParNom(selectOeuvres.getSelectionModel().getSelectedItem());
				
			//save data in Gestion Exemplaire
			gestionExemplaire.ajouterExemplaire(etat, oeuvre);
			gestionOeuvre.setNbTotalDisponibles(oeuvre);
			result.setText("L'exemplaire a été ajouté !");
			result.setTextFill(Color.GREEN);
	      } else {
	        result.setText("Veuillez remplir tout les champs !");
			result.setTextFill(Color.RED);
	       }
    	} catch(Exception e) {
			result.setText("Impossible d'ajouter l'exemplaire ! ");
			result.setTextFill(Color.RED);
		}
	}
	
	@FXML
    public void modFormEx(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/exemplaire/formUpdEx.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modifier un exemplaire");
            stage.getIcons().add(new Image("images/open-book.png"));
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
		  if (verifierSelects()) {
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
	        	result.setText("Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
		} catch(Exception e) {
			result.setText(" Impossible de modifier l'exemplaire ! ");
			result.setTextFill(Color.RED);
		}

	}
	
	@FXML 
	public void supprimerExemplaire(ActionEvent event) throws IOException, SQLException {
	 if (tabViewEx.getSelectionModel().getSelectedItem() == null) {
     	resultEx.setText("Veuillez sélectionner un exemplaire à supprimer !");
		resultEx.setTextFill(Color.RED);
		} else {
			Exemplaire exemplaire = tabViewEx.getSelectionModel().getSelectedItem();
			if (exemplaire.getEtat().equals("Abimé") && exemplaire.getDateArchivage() == null) {
				//2
				gestionOeuvre.setNbTotalSup(exemplaire.getOeuvre());
				
			} else if  (!exemplaire.getEtat().equals("Abimé") && exemplaire.getDateArchivage() == null) {
				//4
				gestionOeuvre.setNbTotalDispoSup(exemplaire.getOeuvre());
			} else {
				//1 && 3
				LOG.finer("je ne suis pas abimé et je suis  archivé");
			}
			if (!exemplaire.getStatut().equals("Emprunté")) {
				int exemplaireID = exemplaire.getId();
				gestionExemplaire.supprimerExemplaire(exemplaireID);
				resultEx.setText("L'exemplaire avec l'ID " + exemplaireID + " a été supprimé !");
				resultEx.setTextFill(Color.GREEN);
				getListExemplars();
			} else {
				resultEx.setText("impossible de supprimer un exemplaire emprunté !");
				resultEx.setTextFill(Color.RED);
			}
			getListExemplars();
		}
	}
	
	@FXML
    public void archiverExemplaire(ActionEvent event) throws IOException, SQLException {
        if (tabViewEx.getSelectionModel().getSelectedItem() == null) {
        	resultEx.setText("veuillez sélectionner un exemplaire à archiver !");
        	resultEx.setTextFill(Color.RED);
		} else {
			Exemplaire exemplaire = tabViewEx.getSelectionModel().getSelectedItem();
			if (!exemplaire.getStatut().equals("Emprunté")) {
				int exemplaireID = exemplaire.getId();
				resultEx.setText("l'exemplaire avec l'ID " + exemplaireID + " a été archivé !");
				resultEx.setTextFill(Color.GREEN);
				if (exemplaire.getEtat().equals("Abimé")) {
					gestionOeuvre.setNbTotalSup(exemplaire.getOeuvre());
				} else {
					gestionOeuvre.setNbTotalDispoSup(exemplaire.getOeuvre());
					gestionEmprunt.setStatutExemplaire(exemplaire,"Indisponible");
				}
				gestionExemplaire.archiverExemplaire(exemplaireID);
			} else {
				resultEx.setText("impossible d'archiver un exemplaire emprunté !");
				resultEx.setTextFill(Color.RED);
			}
			getListExemplars();
		}
		
    }
	
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
			Exemplaire modExemplaire = gestionExemplaire.trouverExemplaireParID(exemplaireID);
			
			titre.setText(modExemplaire.getTitre());
			selectEtats.getSelectionModel().select(modExemplaire.getEtat());
		}
	}
	
	private boolean verifierChamps() {

        boolean validTextFields = true;

        if (selectOeuvres.getSelectionModel().getSelectedItem() == null) {
            validTextFields = false;
            selectOeuvres.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-color: #B22222;");
        }

        if (selectEtats.getSelectionModel().getSelectedItem() == null) {
            validTextFields = false;
            selectEtats.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-color: #B22222;");
        }

        return validTextFields;
    }
	
	private boolean verifierSelects() {

        boolean validTextFields = true;

        if (select.getSelectionModel().getSelectedItem() == null) {
            validTextFields = false;
            select.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-color: #B22222;");
        }

        if (selectEtats.getSelectionModel().getSelectedItem() == null) {
            validTextFields = false;
            selectEtats.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-color: #B22222;");
        }

        return validTextFields;
    }
	
}
