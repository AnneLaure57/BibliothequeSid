package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Usager;
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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IHM_Usager implements Initializable{
	
	/*
	 *  Attributs
	 */
	
	private static final Logger LOG = Logger.getLogger(Gestion_Usager.class.getName());
	
	private Gestion_Usager gestionUsager = new Gestion_Usager();
	
	@FXML
	private Parent root;
	
	@FXML private Button submit;
	@FXML private Button cancel;
	@FXML private Button actualize;
	
	@FXML private ComboBox select;
	
	@FXML private Label result;
	@FXML private Label resultU;
	
	@FXML private TextField lastname;
	@FXML private TextField firstname;
	@FXML private DatePicker dateB;
	@FXML private TextField adress;
	@FXML private TextField city;
	@FXML private TextField cp;
	@FXML private TextField mail;
	@FXML private TextField tel;
	@FXML private TextField searchUsager;
	
	@FXML TableView<Usager> tabViewU;
	@FXML TableColumn<Usager, Number> tabIdU;
	@FXML TableColumn<Usager, String> tabNameU;
	@FXML TableColumn<Usager, String> tabFNameU;
	@FXML TableColumn<Usager, String> tabAdresU;
	@FXML TableColumn<Usager, Number> tabCPU;
	@FXML TableColumn<Usager, String> tabCityU;
	@FXML TableColumn<Usager, String> tabTelU;
	@FXML TableColumn<Usager, String> tabMailU;
	@FXML TableColumn<Usager, Date> tabDateBU;
	@FXML TableColumn<Usager, Date> tabDateCU;
	@FXML TableColumn<Usager, Date> tabDateDU;
	
	/*
	 * initialize main view user
	 */
	
	@Override
   	public void initialize(URL location, ResourceBundle resources) {
	
		if (location.equals(getClass().getClassLoader().getResource("view/usager/UsagerView.fxml"))) {
			tabIdU.setCellValueFactory(new PropertyValueFactory<Usager, Number>("id"));
			tabNameU.setCellValueFactory(new PropertyValueFactory<Usager, String>("nom"));
			tabFNameU.setCellValueFactory(new PropertyValueFactory<Usager, String>("prenom"));
			tabAdresU.setCellValueFactory(new PropertyValueFactory<Usager, String>("adresse"));
			tabCPU.setCellValueFactory(new PropertyValueFactory<Usager, Number>("codepostal"));
			tabCityU.setCellValueFactory(new PropertyValueFactory<Usager, String>("ville"));
			tabTelU.setCellValueFactory(new PropertyValueFactory<Usager, String>("telephone"));
			tabMailU.setCellValueFactory(new PropertyValueFactory<Usager, String>("mail"));
			tabDateBU.setCellValueFactory(new PropertyValueFactory<Usager, Date>("dateNaissance"));
			tabDateCU.setCellValueFactory(new PropertyValueFactory<Usager, Date>("dateCreation"));
			tabDateDU.setCellValueFactory(new PropertyValueFactory<Usager, Date>("dateArchivage"));
		
			tabNameU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
			tabFNameU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
			tabAdresU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
			tabCPU.setCellFactory(TextFieldTableCell.<Usager, Number>forTableColumn(new NumberStringConverter()));
			tabCityU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
			tabTelU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
			tabMailU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
			
			resultU.setText("aucune action effectuée !");
			resultU.setTextFill(Color.BLUE);
			
			getListUsers();
		}
			
		if (location.equals(getClass().getClassLoader().getResource("view/usager/formUpdU.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListUsersSelect();
            	
		}
		
	}
	
	/*
	 * Close view
	 */
	
	@FXML
    private void fermerVue() {
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }    
	
	/*
	 * List all users in tableView
	 */
	
	public void getListUsers() {
		tabViewU.setItems(gestionUsager.listerUsagers());
	}
	
	/*
	 * Get the list of users inside select for modForm
	 */
	
	public void getListUsersSelect() {
		select.setItems(gestionUsager.listerUsagersDispo());
	}
	
	/*
	 * Find a user by lastname/firstname research
	 */
	
	@FXML
	public void trouverUsager () {
		ObservableList<Usager> list =gestionUsager.trouverUsager(searchUsager.getText());
		LOG.fine(searchUsager.getText());
		tabViewU.setItems(list);
		resultU.setText("nombres d'éléments trouvé(s) : " + list.size());
	}
	
	/*
	 * Select a user for form upd
	 */
	
	@FXML
	public void selectUsager() {
		if (select.getSelectionModel().getSelectedItem() == null) {
        	result.setText("Veuillez selectionner un usager à modifier !");
			result.setTextFill(Color.RED);
		} else {
			Usager usager = (Usager) select.getSelectionModel().getSelectedItem();
			int usagerID = usager.getId();
			//auto complement fields
			Usager modusager = gestionUsager.trouverUsager(usagerID);
			lastname.setText(modusager.getNom());
			firstname.setText(modusager.getPrenom());
			adress.setText(modusager.getAdresse());
			city.setText(modusager.getVille());
			mail.setText(modusager.getMail());
            cp.setText(Integer.toString(modusager.getCodepostal()));
            tel.setText(modusager.getTelephone());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateB.setPromptText(dateFormat.format(modusager.getDateNaissance()));
		}
	}
	
	/*
     * Actualize the list of users
     */
	
	@FXML
	public void actualiserListe () {
		getListUsers();
		resultU.setText("actualisation terminée !");
		resultU.setTextFill(Color.BLUE);
	}
	
	/*
     * Open Add's form
     */
	
	@FXML
	public void ajoutFormU(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/usager/formAddU.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel usager");
            stage.getIcons().add(new Image("images/open-book.png"));
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
                       
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    @FXML
	private void ajouterUsager(ActionEvent event) { 	
    	try {
			if (verifierChamps()) {
				//get values from form
				String nom = lastname.getText();
				String prenom = 	firstname.getText();
				java.sql.Date dateNaissance = java.sql.Date.valueOf(dateB.getValue());
				String adresse = 	adress.getText();
				int codePostal = 	Integer.parseInt(cp.getText());
				String ville = 	city.getText();
				String email = 	mail.getText();
				String telephone = 	tel.getText();
				
				//save data in Gestion Usager
				gestionUsager.ajouterUsager(nom,prenom, adresse,codePostal, ville, telephone, email , dateNaissance);
				result.setText("L'usager a été ajouté !");
	    		result.setTextFill(Color.GREEN);
	        } else {
	        	result.setText("Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
    	} catch(Exception e) {
			result.setText(" Impossible d'ajouter l'usager ! ");
			result.setTextFill(Color.RED);
		}
	}
    
    /*
     * Open Update's form
     */

    @FXML
    public void modFormU(ActionEvent event) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/usager/formUpdU.fxml"));
			root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Modifier un usager");
            stage.getIcons().add(new Image("images/open-book.png"));
			stage.setScene(new Scene(root));  
			stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

	@FXML
	private void modifierUsager(ActionEvent event) {
		try {
			if (verifierChamps()) {
				//get values from form
				Usager us = (Usager) select.getSelectionModel().getSelectedItem();
				int id = us.getId();
				String nom = lastname.getText();
				String prenom = 	firstname.getText();
				java.sql.Date dateNaissance = java.sql.Date.valueOf(dateB.getValue());
				String adresse = 	adress.getText();
				int codePostal = 	Integer.parseInt(cp.getText());
				String ville = 	city.getText();
				String email = 	mail.getText();
				String telephone = 	tel.getText();
				
				//save data in Gestion Usager
				gestionUsager.modifierUsager(id,nom,prenom, adresse,codePostal, ville, telephone, email , dateNaissance);
				result.setText("L'usager a été modifié !");
				result.setTextFill(Color.GREEN);
	        } else {
	        	result.setText("Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
    	} catch(Exception e) {
			result.setText(" Impossible de modifier l'usager ! ");
			result.setTextFill(Color.RED);
		}
	}
    
    @FXML
    public void supprimerUsager(ActionEvent event) throws IOException, SQLException {
        if (tabViewU.getSelectionModel().getSelectedItem() == null) {
        	resultU.setText("veuillez sélectionner un usager à supprimer !");
			resultU.setTextFill(Color.RED);
		} else {
			Usager usager = tabViewU.getSelectionModel().getSelectedItem();
			int usagerID = usager.getId();
			resultU.setText("L'usager avec l'ID " + usagerID + " a été supprimé !");
			resultU.setTextFill(Color.GREEN);
			gestionUsager.supprimerUsager(usagerID);
			getListUsers();
		}
		
    };
    
    @FXML
    public void archiverUsager(ActionEvent event) throws IOException, SQLException {
        if (tabViewU.getSelectionModel().getSelectedItem() == null) {
        	resultU.setText("veuillez sélectionner un usager à archiver !");
			resultU.setTextFill(Color.RED);
		} else {
			Usager usager = tabViewU.getSelectionModel().getSelectedItem();
			int usagerID = usager.getId();
			resultU.setText("L'usager avec l'ID " + usagerID + " a été archivé !");
			resultU.setTextFill(Color.GREEN);
			gestionUsager.archiverUsager(usagerID);
			getListUsers();
		}
		
    };
    
    /*
     *  Vérifier les champs
     */
    
    private boolean verifierChamps() {

        boolean validTextFields = true;

        if (lastname.getText().isEmpty()) {
            validTextFields = false;
            lastname.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        if (firstname.getText().isEmpty()) {
            validTextFields = false;
            firstname.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        if (adress.getText().isEmpty()) {
            validTextFields = false;
            adress.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        if (city.getText().isEmpty()) {
            validTextFields = false;
            city.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        if (cp.getText().isEmpty()) {
            validTextFields = false;
            cp.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        if (dateB.getValue() == null) {
            validTextFields = false;
            dateB.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-color: #B22222;");
        }
        
        if (tel.getText().isEmpty()) {
            validTextFields = false;
            tel.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        if (mail.getText().isEmpty()) {
            validTextFields = false;
            mail.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        return validTextFields;
    }
    
}
