package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Reservation;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Auteur;
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

public class IHM_Reservation implements Initializable{
	
    private static final Logger LOG = Logger.getLogger(Gestion_Reservation.class.getName());
	
	private Gestion_Reservation gestionReservation = new Gestion_Reservation();
	
	private Gestion_Oeuvre gestionOeuvre = new Gestion_Oeuvre();
	
	@FXML
	private Parent root;
	
	@FXML private Button submit;
	@FXML private Button cancel;
	@FXML private Button actualize;
	
	@FXML private ComboBox selectU;
	@FXML private ComboBox selectO;
	
	@FXML private DatePicker dateRes;
	
	@FXML private TextField searchReservation;
	
	@FXML private Label result;
	@FXML private Label resultRes;
	
	@FXML TableView<Reservation> tabViewRes;
	@FXML TableColumn<Reservation, Number> tabIdRes;
	@FXML TableColumn<Reservation, String> tabTitleRes;
	@FXML TableColumn<Reservation, String> tabUsager;
	@FXML TableColumn<Reservation, String> tabStatut;
	@FXML TableColumn<Reservation, Date> tabDateR;
	@FXML TableColumn<Reservation, Date> tabDateAn;
	@FXML TableColumn<Reservation, Date> tabDateAr;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (location.equals(getClass().getClassLoader().getResource("view/reservation/ReservationView.fxml"))) {
			tabIdRes.setCellValueFactory(new PropertyValueFactory<Reservation, Number>("id"));
			tabTitleRes.setCellValueFactory(new PropertyValueFactory<Reservation, String>("titre"));
			tabUsager.setCellValueFactory(new PropertyValueFactory<Reservation, String>("nomPrenom"));
			tabStatut.setCellValueFactory(new PropertyValueFactory<Reservation, String>("statut"));
			tabDateR.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("dateReservation"));
			tabDateAn.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("dateAnnulation"));
			tabDateAr.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("dateArchivage"));
			
			resultRes.setText("Aucune action effectuée !");
			resultRes.setTextFill(Color.BLUE);
			
			getListReservations();
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/reservation/formAddRes.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListUsagersSelect();
            getListOeuvresSelect();
            	
		}
		
	};
	
	@FXML
	public void actualizeList () {
		getListReservations();
	}
	
	@FXML
    private void closeView(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }    
	
	/*
	 * List all reservations in tableView
	 */
	
	public void getListReservations()
	{
		tabViewRes.setItems(gestionReservation.ListerReservations());
	}
	
	/*
	 * Get the list of Usagers inside select for addFrom
	 */
	
	public void getListUsagersSelect()
	{
		selectU.setItems(gestionReservation.ListerUsagersUnDeleted());
	}
	
	/*
	 * Get the list of Oeuvres inside select for addFrom
	 */
	
	public void getListOeuvresSelect()
	{
		selectO.setItems(gestionReservation.ListerOeuvresUnDeleted());
	}
	
	/*
	 * Find a reservation by titre/statut/F + L name research
	 */
	
	@FXML
	public void trouverReservation () {
		ObservableList<Reservation> list = gestionReservation.trouverReservation(searchReservation.getText());
		LOG.fine(searchReservation.getText());
		tabViewRes.setItems(list);
	}
    
	
	/*
	 *  Add Methods Reservations
	 */
	
	@FXML
	public void formAddRes(ActionEvent event) {
        try {
        	//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/formAddU.fxml"));
        	//Parent part = fxmlLoader.load();
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/reservation/formAddRes.fxml"));
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
    
    /*
     *  Add reservation
     */
    
    @FXML
   	private void reserverOeuvre(ActionEvent event) {
   		try {
			if (textFieldsValid()) {
				//get values from form
				Oeuvre oeuvre = (Oeuvre) selectO.getSelectionModel().getSelectedItem();
	   	    	
	   	    	Usager usager = (Usager) selectU.getSelectionModel().getSelectedItem();
	   			
	   			java.sql.Date dateReservation = java.sql.Date.valueOf(dateRes.getValue());
	   	   		
	   	   		//save data in Gestion Back
	   	   		gestionReservation.ajouterReservation(oeuvre,usager,dateReservation);
	   	   		gestionOeuvre.setNbResaAdd(oeuvre);
	   	   		result.setText("La réservation a été ajouté !");
	   	   		result.setTextFill(Color.GREEN);
	        } else {
	        	result.setText("  Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
    	} catch(Exception e) {
    		result.setText(" La réservation entrée existe déja dans la BDD !");
			result.setTextFill(Color.GREEN);
		}
   	}
    
    /*
	 *  Delete Methods
	 */

    public void annulerReservation(ActionEvent event) {
    	 if (tabViewRes.getSelectionModel().getSelectedItem() == null) {
         	resultRes.setText("Veuillez sélectionner une réservation à annuler !");
         	resultRes.setTextFill(Color.RED);
 		} else {
 			Reservation reservation = tabViewRes.getSelectionModel().getSelectedItem();
 			int reservationID = reservation.getId();
 			resultRes.setText("La réservation avec l'ID " + reservationID + " a été supprimé !");
 			resultRes.setTextFill(Color.GREEN);
 			gestionReservation.annulerReservation(reservationID);
 			if (reservation.getDateArchivage() == null) {
				gestionOeuvre.setNbResaRem(reservation.getOeuvre());
			}
 			getListReservations();
 		}
        
    }
    
    @FXML
    public void supprimerReservation(ActionEvent event) throws IOException, SQLException {
        if (tabViewRes.getSelectionModel().getSelectedItem() == null) {
        	resultRes.setText("Veuillez sélectionner une réservation à supprimer !");
        	resultRes.setTextFill(Color.RED);
		} else {
			Reservation reservation = tabViewRes.getSelectionModel().getSelectedItem();
			int reservationID = reservation.getId();
			resultRes.setText("La réservation avec l'ID " + reservationID + " a été supprimé !");
			resultRes.setTextFill(Color.GREEN);
			if (reservation.getDateArchivage() == null && reservation.getDateAnnulation() == null) {
				gestionOeuvre.setNbResaRem(reservation.getOeuvre());
			}
			gestionReservation.supprimerReservation(reservationID);
			getListReservations();
		}
		
    };
    
    @FXML
    public void archiverReservation(ActionEvent event) throws IOException, SQLException {
        if (tabViewRes.getSelectionModel().getSelectedItem() == null) {
        	resultRes.setText("Veuillez sélectionner une réservation à archiver !");
        	resultRes.setTextFill(Color.RED);
		} else {
			Reservation reservation = tabViewRes.getSelectionModel().getSelectedItem();
			int reservationID = reservation.getId();
			resultRes.setText("La réservation avec l'ID " + reservationID + " a été archivé !");
			resultRes.setTextFill(Color.GREEN);
			gestionReservation.archiverReservation(reservationID);
			gestionReservation.annulerReservation(reservationID);
			if (reservation.getDateAnnulation() == null) {
				gestionOeuvre.setNbResaRem(reservation.getOeuvre());
			}
			getListReservations();
		}
		
    };
    
    private boolean textFieldsValid() {

        boolean validTextFields = true;
        
        if (dateRes.getValue() == null) {
            validTextFields = false;
            dateRes.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        return validTextFields;
    }

}
