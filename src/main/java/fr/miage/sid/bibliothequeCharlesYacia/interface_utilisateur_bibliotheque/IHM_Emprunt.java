package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Emprunt;
import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Reservation;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Emprunt;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Reservation;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IHM_Emprunt implements Initializable{
	
	private static final Logger LOG = Logger.getLogger(Gestion_Emprunt.class.getName());
		
	private Gestion_Emprunt gestionEmprunt = new Gestion_Emprunt();
	
	private Gestion_Exemplaire gestionExemplaire = new Gestion_Exemplaire();
	
	@FXML
	private Parent root;
	
	@FXML private Button submit;
	@FXML private Button cancel;
	@FXML private Button actualize;
	
	@FXML private ComboBox selectUs;
	@FXML private ComboBox selectOe;
	@FXML private ComboBox selectEx;
	@FXML private ComboBox selectEm;
	@FXML private ComboBox selectEtats;
	
	@FXML private DatePicker dateEm;
	@FXML private DatePicker dateRe;
	
	@FXML private TextField searchEmprunt;
	
	@FXML private Label result;
	@FXML private Label resultEm;
	
	@FXML TableView<Emprunt> tabViewEm;
	@FXML TableColumn<Emprunt, Number> tabIdEm;
	@FXML TableColumn<Emprunt, String> tabTitleO;
	@FXML TableColumn<Emprunt, String> tabUsager;
	@FXML TableColumn<Emprunt, Number> tabIdEx;
	@FXML TableColumn<Emprunt, String> tabStatut;
	@FXML TableColumn<Emprunt, Date> tabDateEm;
	@FXML TableColumn<Emprunt, Date> tabDateRe;
	@FXML TableColumn<Emprunt, Date> tabDateAr;
	
	public void initialize(URL location, ResourceBundle resources){
		if (location.equals(getClass().getClassLoader().getResource("view/emprunt/EmpruntView.fxml"))) {
			tabIdEm.setCellValueFactory(new PropertyValueFactory<Emprunt, Number>("id"));
			tabTitleO.setCellValueFactory(new PropertyValueFactory<Emprunt, String>("titre"));
			tabUsager.setCellValueFactory(new PropertyValueFactory<Emprunt, String>("nomPrenom"));
			tabIdEx.setCellValueFactory(new PropertyValueFactory<Emprunt, Number>("numero"));
			tabStatut.setCellValueFactory(new PropertyValueFactory<Emprunt, String>("statut"));
			tabDateEm.setCellValueFactory(new PropertyValueFactory<Emprunt, Date>("dateEmprunt"));
			tabDateRe.setCellValueFactory(new PropertyValueFactory<Emprunt, Date>("dateRetour"));
			tabDateAr.setCellValueFactory(new PropertyValueFactory<Emprunt, Date>("dateArchivage"));
			
			resultEm.setText("Aucune action effectuée !");
			resultEm.setTextFill(Color.BLUE);
			
			getListEmprunts();
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/emprunt/formAddEm.fxml"))) {
			
            result.setText("Aucun ajout enregistré !");
            result.setTextFill(Color.BLUE);
            getListUsagersSelect();
            getListOeuvresSelect();
            	
		}
		
       if (location.equals(getClass().getClassLoader().getResource("view/emprunt/formUpdEm.fxml"))) {
            result.setText("Aucune modification enregistré !");
            result.setTextFill(Color.BLUE);
            getListUsagersSelect();
            getListEtatSelect();
            	
		}
	}
	
	@FXML
	public void actualizeList () {
		getListEmprunts();
	}
	
	@FXML
    private void closeView(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    } 

	public void getListOeuvresSelect() {
		selectOe.setItems(gestionEmprunt.ListerOeuvresUnDeleted());
	}
	
	@FXML
	public void selectExemplaires() {
		if (selectOe.getSelectionModel().getSelectedItem() == null) {
        	result.setText("Veuillez selectionner une oeuvre !");
			result.setTextFill(Color.RED);
		} else {
			Oeuvre oeuvre = (Oeuvre) selectOe.getSelectionModel().getSelectedItem();
			int oeuvreID = oeuvre.getId();
			//auto complement fields
			selectEx.setItems(gestionEmprunt.ListerExemplairesUnDeleted(oeuvreID));
		}
	}

	public void getListUsagersSelect() {
		selectUs.setItems(gestionEmprunt.ListerUsagersUnDeleted());
	}

	public void getListEmprunts() {
		tabViewEm.setItems(gestionEmprunt.ListerEmprunts());
		
	}
	
	public void getListEtatSelect() {
		selectEtats.getItems().addAll( "Neuf","Bon","Moyen","Abimé");
	}
	
	/*
	 * List all loans for an usager (rendre exemplaire)
	 */
	
	@FXML
	public void selectEmpruntsUsager() {
		if (selectUs.getSelectionModel().getSelectedItem() == null) {
        	result.setText("Veuillez selectionner un usager !");
			result.setTextFill(Color.RED);
		} else {
			Usager usager = (Usager) selectUs.getSelectionModel().getSelectedItem();
			int usagerID = usager.getId();
			//auto complement fields
			selectEm.setItems(gestionEmprunt.ListerEmpruntsUsager(usagerID));
		}
	}
	
	/*
	 * Find a emprunt by titre/statut/F + L name research
	 */
	
	@FXML
	public void trouverEmprunt() {
		ObservableList<Emprunt> list = gestionEmprunt.trouverEmprunt(searchEmprunt.getText());
		LOG.fine(searchEmprunt.getText());
		tabViewEm.setItems(list);
	}

	@FXML
	public void formAddEm(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/emprunt/formAddEm.fxml"));
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
    
    @FXML
    public void emprunterExemplaire(ActionEvent event) {
    	try
		{
   	    	Usager usager = (Usager) selectUs.getSelectionModel().getSelectedItem();
   	    	
   	    	Oeuvre oeuvre = (Oeuvre) selectOe.getSelectionModel().getSelectedItem();
   	    	
   	    	Exemplaire exemplaire = (Exemplaire) selectEx.getSelectionModel().getSelectedItem();
   			
   			java.sql.Date dateEmprunt = java.sql.Date.valueOf(dateEm.getValue());
   			
   			java.sql.Date dateRetour = java.sql.Date.valueOf(dateRe.getValue());
   	   		
   	   		//save data in Gestion Exemplaire
   	   		gestionEmprunt.emprunterExemplaire(oeuvre,usager,exemplaire, dateEmprunt, dateRetour);
   	   		//TODO set state exemplaire
   	     	gestionEmprunt.setStatutExemplaire(exemplaire, "Emprunté");
   	   		result.setText("L'emprunt a été ajouté !");
   	   		result.setTextFill(Color.GREEN);
		}
		catch(Exception e)
		{
			result.setText(" L'emprunt entré existe déja dans la BDD !");
			result.setTextFill(Color.GREEN);
		}
    };
    
    @FXML
	public void formUpdEm(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/emprunt/formUpdEm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modifier un emprunt");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    @FXML
    public void rendreExemplaire(ActionEvent event) {
    	
    	try
		{
   	    	Emprunt emprunt = (Emprunt) selectEm.getSelectionModel().getSelectedItem();
   	    	System.out.println(emprunt.toString());
   	    	
   	    	Exemplaire exemplaire = emprunt.getExemplaire();
   	    	int empruntID = emprunt.getId();
   			
   			java.sql.Date dateRetour = java.sql.Date.valueOf(dateRe.getValue());
   			
   			String etat = selectEtats.getSelectionModel().getSelectedItem().toString();
   	   		
   	   		//save data in Gestion Exemplaire
   			if (!emprunt.getDateRetour().equals(dateRetour)) {
   	   	   		gestionEmprunt.rendreExemplaire(empruntID,"En retard",dateRetour);
   			} else {
   	   			gestionEmprunt.rendreExemplaire(empruntID,"Rendu",dateRetour);
   			}
   	   		gestionExemplaire.modifierExemplaire(exemplaire, etat);
   	   		System.out.println();
   	     	if (etat.equals("Abimé")) {
   	     	  gestionEmprunt.setStatutExemplaire(exemplaire,"Indisponible");
   	     	} else {
   	     	  gestionEmprunt.setStatutExemplaire(exemplaire,"Disponible");
   	     	}
   	   		result.setText("L'emprunt a été modifié !");
   	   		result.setTextFill(Color.GREEN);
		}
		catch(Exception e)
		{
			result.setText(" Aucun emprunt ne correspond aux informations saisies");
			result.setTextFill(Color.GREEN);
		}
       
    };
    
    @FXML
    public void supprimerEmprunt(ActionEvent event) throws IOException, SQLException {
        if (tabViewEm.getSelectionModel().getSelectedItem() == null) {
        	resultEm.setText("Veuillez sélectionner un emprunt à supprimer !");
        	resultEm.setTextFill(Color.RED);
		} else {
			Emprunt emprunt = tabViewEm.getSelectionModel().getSelectedItem();
			int empruntID = emprunt.getId();
			resultEm.setText("L'emprunt avec l'ID " + empruntID + " a été supprimé !");
			resultEm.setTextFill(Color.GREEN);
			gestionEmprunt.supprimerEmprunt(empruntID);
			getListEmprunts();
		}
		
    };
    
    @FXML
    public void archiverEmprunt(ActionEvent event) throws IOException, SQLException {
        if (tabViewEm.getSelectionModel().getSelectedItem() == null) {
        	resultEm.setText("Veuillez sélectionner un emprunt à archiver !");
        	resultEm.setTextFill(Color.RED);
		} else {
			Emprunt emprunt = tabViewEm.getSelectionModel().getSelectedItem();
			int empruntID = emprunt.getId();
			resultEm.setText("La réservation avec l'ID " + empruntID + " a été archivé !");
			resultEm.setTextFill(Color.GREEN);
			gestionEmprunt.archiverEmprunt(empruntID);
			getListEmprunts();
		}
		
    };
}