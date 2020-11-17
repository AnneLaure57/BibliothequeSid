package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Back;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class IHM_Back {
	
	/*
	 *  Attributs
	 */
	
	private static final Logger LOG = Logger.getLogger(Gestion_Back.class.getName());
	
	private Gestion_Back gestionBack = new Gestion_Back();
	
	@FXML
	private Parent root;
	
	@FXML private Button submit;
	@FXML private Button cancel;
	@FXML private Button actualize;
	
	@FXML private Label result;
	@FXML private Label resultU;
	@FXML private Label resultO;
	@FXML private Label resultEx;
	
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
	
	@FXML
   	public void initialize() throws IOException {
	
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
		tabDateDU.setCellValueFactory(new PropertyValueFactory<Usager, Date>("dateSuppression"));
	
		tabNameU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
		tabFNameU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
		tabAdresU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
		tabCPU.setCellFactory(TextFieldTableCell.<Usager, Number>forTableColumn(new NumberStringConverter()));
		tabCityU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
		tabTelU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
		tabMailU.setCellFactory(TextFieldTableCell.<Usager>forTableColumn());
		
		resultU.setText("Aucune action effectuée !");
		
		getListUsagers();
		
	}
	
	@FXML
    private void closeView(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }    
	
	/*
	 *  Usager Methods
	 */
	
	public void getListUsagers()
	{
		System.out.println("je suis ici");
		tabViewU.setItems(gestionBack.ListerUsagers());
	}
	
	@FXML
	public void trouverUsager () {
		ObservableList<Usager> list = gestionBack.trouverUsager(searchUsager.getText());
		LOG.fine(searchUsager.getText());
		tabViewU.setItems(list);
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
		result.setText("L'usager a été ajouté");
		result.setTextFill(Color.GREEN);
		//Pour actualiser la liste TODO : resolve, the list is not actualize
		getListUsagers();
	}
    
    @FXML
    public void modFormU(ActionEvent event) {
        try {
        	if (tabViewU.getSelectionModel().getSelectedItem() == null) {
            	resultU.setText("Veuillez selectionner un usager à modifier avant !");
    			resultU.setTextFill(Color.RED);
    		} else {
    			Usager usager = tabViewU.getSelectionModel().getSelectedItem();
    			int usagerID = usager.getId();
//    			resultU.setText("L'usager avec l'ID : " + usagerID + " a été modifié !");
//    			resultU.setTextFill(Color.GREEN);
    			gestionBack.trouverUsager(usagerID);
    		}
        	/*Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formUpdU.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modifier un usager");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    };
    
    @FXML
	private void modifierUsager(ActionEvent event) {
    	
		/*LOG.fine(lastname.getText() + ", " + firstname.getText() + ", " + dateB.getValue() + ", " + adress.getText() + ", " + cp.getText() + ", " + city.getText()
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
		//Pour actualiser la liste
		getListUsagers();*/
		
	}
    
    @FXML
    public void supprimerUsager(ActionEvent event) throws IOException, SQLException {
        if (tabViewU.getSelectionModel().getSelectedItem() == null) {
        	resultU.setText("Veuillez selectionner un usager supprimer avant !");
			resultU.setTextFill(Color.RED);
		} else {
			Usager usager = tabViewU.getSelectionModel().getSelectedItem();
			int usagerID = usager.getId();
			resultU.setText("L'usager avec l'ID : " + usagerID + " a été supprimé !");
			resultU.setTextFill(Color.GREEN);
			gestionBack.supprimerUsager(usagerID);
			getListUsagers();
		}
		
    };
    
    /*
     * 
     */
    
    @FXML
	public void ajouterExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formAddEx.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel exemplaire");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    @FXML
    public void ajouterOeuvre(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formAddO.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter une nouvelle oeuvre");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    /*
	 *  Update Methods
	 */
    
    @FXML
    public void modifierExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formUpdEx.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Modifier un exemplaire");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
   
    
    /*
	 *  Delete Methods
	 */
    
    
    
    @FXML
    public void supprimerExemplaire(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formDelEx.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Supprimer un exemplaire");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    @FXML
    public void supprimerOeuvre(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/formDelO.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Supprimer une oeuvre");
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
//	public void ajouterExemplaire(Oeuvre oeuvre, String titre) {
//		throw new UnsupportedOperationException();
//	}
//
//	public void ajouterUsager(String nom) {
//		
//		throw new UnsupportedOperationException();
//	}
//
//	public void ajouterOeuvre(String titre) {
//		throw new UnsupportedOperationException();
//	}
//
//	public void supprimerExemplaire(Oeuvre oeuvre) {
//		throw new UnsupportedOperationException();
//	}
//
//	public void supprimerOeuvre(String titre) {
//		throw new UnsupportedOperationException();
//	}
//
//	public void supprimerUsager(String nom) {
//		throw new UnsupportedOperationException();
//	}
//
//	public void modifierUsager(Usager usager) {
//		throw new UnsupportedOperationException();
//	}
//
//	public void modifierExemplaire(Exemplaire exemplaire) {
//		throw new UnsupportedOperationException();
//	}
}
