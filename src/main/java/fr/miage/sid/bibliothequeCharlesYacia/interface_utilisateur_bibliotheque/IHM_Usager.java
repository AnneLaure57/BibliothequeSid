package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IHM_Usager implements Initializable{
	
	/*
	 *  Attributs
	 */
	
	private static final Logger LOG = Logger.getLogger(Gestion_Usager.class.getName());
	
	private Gestion_Usager gestionBack = new Gestion_Usager();
	
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
	
	@Override
   	public void initialize(URL location, ResourceBundle resources){
	
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
			
			resultU.setText("Aucune action effectuée !");
			resultU.setTextFill(Color.BLUE);
			
			getListUsagers();
		}
			
		if (location.equals(getClass().getClassLoader().getResource("view/usager/formUpdU.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListUsagersSelect();
            	
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
	 * List all usagers in tableView
	 */
	
	public void getListUsagers()
	{
		tabViewU.setItems(gestionBack.ListerUsagers());
	}
	
	/*
	 * Get the list of Usagers inside select for modForm
	 */
	
	public void getListUsagersSelect()
	{
		select.setItems(gestionBack.ListerUsagersUnDeleted());
	}
	
	/*
	 * Find a usager by lastname/firstname research
	 */
	
	@FXML
	public void trouverUsager () {
		ObservableList<Usager> list = gestionBack.trouverUsager(searchUsager.getText());
		LOG.fine(searchUsager.getText());
		tabViewU.setItems(list);
	}
	
	@FXML
	public void selectUsager() {
		if (select.getSelectionModel().getSelectedItem() == null) {
        	result.setText("Veuillez selectionner un usager à modifier avant !");
			result.setTextFill(Color.RED);
		} else {
			Usager usager = (Usager) select.getSelectionModel().getSelectedItem();
			int usagerID = usager.getId();
			//auto complement fields
			Usager modusager = gestionBack.trouverUsager(usagerID);
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
	
	@FXML
	public void actualizeList () {
		getListUsagers();
	}
	
	@FXML
	public void ajoutFormU(ActionEvent event) {
        try {
        	//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/formAddU.fxml"));
        	//Parent part = fxmlLoader.load();
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/usager/formAddU.fxml"));
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
    	if (! textFieldsValid()) {
            // one or more of the text fields are empty
    		result.setText("Veuillez remplir les champs manquants !");
    		result.setTextFill(Color.RED);
            return;
        }
		LOG.fine(lastname.getText() + ", " + firstname.getText() + ", " + dateB.getValue() + ", " + adress.getText() + ", " + cp.getText() + ", " + city.getText()
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
		gestionBack.ajouterUsager(nom,prenom, adresse,codePostal, ville, telephone, email , dateNaissance);
		result.setText("L'usager a été ajouté !");
		result.setTextFill(Color.GREEN);
	}

    @FXML
    public void modFormU(ActionEvent event) {
        try {
//        	if (tabViewU.getSelectionModel().getSelectedItem() == null) {
//            	resultU.setText("Veuillez selectionner un usager à modifier avant !");
//    			resultU.setTextFill(Color.RED);
//    		} else {
//    			Usager usager = tabViewU.getSelectionModel().getSelectedItem();
//    			int usagerID = usager.getId();
////    			resultU.setText("L'usager avec l'ID : " + usagerID + " a été modifié !");
////    			resultU.setTextFill(Color.GREEN);
//    			Usager modusager = gestionBack.trouverUsager(usagerID);
//    			System.out.println("je suis ici " +modusager.toString() + " nom : "+ modusager.getNom());
//    			//resultU.setText(modusager.getNom());
//    			/*Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/usager/formUpdU.fxml"));
//                Stage stage = new Stage();
//                stage.setTitle("Modifier un usager");
//                Scene scene = new Scene(part);
//                stage.setScene(scene);
//                stage.show();*/
//    			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/usager/formUpdU.fxml"));
//    			root = loader.load();
//    			IHM_Usager controller = loader.getController();
//    	        Stage stage = new Stage();
//    	        stage.setTitle("Modifier un usager");
//    	        stage.setScene(new Scene(root));  
//    	        stage.show();
//    	        //TODO check how get Data between 2 views
//    			getData(modusager);	
//    		}
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/usager/formUpdU.fxml"));
			root = loader.load();
			IHM_Usager controller = loader.getController();
	        Stage stage = new Stage();
	        stage.setTitle("Modifier un usager");
	        stage.setScene(new Scene(root));  
	        stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    };

	@FXML
	private void modifierUsager(ActionEvent event) {
		if (! textFieldsValid()) {
            // one or more of the text fields are empty
    		result.setText("Veuillez remplir les champs manquants !");
    		result.setTextFill(Color.RED);
    		return;
        }
		System.out.println(lastname.getText() + ", " + firstname.getText() + ", " + dateB.getValue() + ", " + adress.getText() + ", " + cp.getText() + ", " + city.getText()
		+ ", " + mail.getText() + ", " + tel.getText());
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
		
		//save data in Gestion Back
		gestionBack.modifierUsager(id,nom,prenom, adresse,codePostal, ville, telephone, email , dateNaissance);
		result.setText("L'usager a été modifié !");
		result.setTextFill(Color.GREEN);
	}
    
    @FXML
    public void supprimerUsager(ActionEvent event) throws IOException, SQLException {
        if (tabViewU.getSelectionModel().getSelectedItem() == null) {
        	resultU.setText("Veuillez sélectionner un usager à supprimer avant !");
			resultU.setTextFill(Color.RED);
		} else {
			Usager usager = tabViewU.getSelectionModel().getSelectedItem();
			int usagerID = usager.getId();
			resultU.setText("L'usager avec l'ID " + usagerID + " a été supprimé !");
			resultU.setTextFill(Color.GREEN);
			gestionBack.supprimerUsager(usagerID);
			getListUsagers();
		}
		
    };
    
    @FXML
    public void archiverUsager(ActionEvent event) throws IOException, SQLException {
        if (tabViewU.getSelectionModel().getSelectedItem() == null) {
        	resultU.setText("Veuillez sélectionner un usager à archiver avant !");
			resultU.setTextFill(Color.RED);
		} else {
			Usager usager = tabViewU.getSelectionModel().getSelectedItem();
			int usagerID = usager.getId();
			resultU.setText("L'usager avec l'ID " + usagerID + " a été supprimé !");
			resultU.setTextFill(Color.GREEN);
			gestionBack.archiverUsager(usagerID);
			getListUsagers();
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
            dateB.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        return validTextFields;
    }
    
}
