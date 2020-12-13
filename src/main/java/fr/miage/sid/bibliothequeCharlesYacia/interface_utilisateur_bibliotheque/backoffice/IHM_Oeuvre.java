package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque.backoffice;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.backoffice.Gestion_Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Auteur;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Livre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
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

public class IHM_Oeuvre implements Initializable{
	
    private static final Logger LOG = Logger.getLogger(Gestion_Oeuvre.class.getName());
    private Gestion_Oeuvre gestionOeuvre = new Gestion_Oeuvre();
    
    @FXML private Button submit;
	@FXML private Button cancel;
	@FXML private Button actualize;
	
	@FXML private ComboBox selectA;
	
	@FXML private Label result;
	@FXML private Label resultO;
	
	@FXML private TextField title;
	@FXML private TextField desc;
	@FXML private DatePicker dateE;
	@FXML private TextField price;
	@FXML private TextField editor;
	@FXML private TextField searchOeuvre;
	
	//Magazine
	@FXML private TextField num;
	@FXML private TextField period;
	
	//Book
	@FXML private TextField resum;
	
	@FXML TableView<Oeuvre> tabViewO;
	@FXML TableColumn<Oeuvre, Number> tabId;
	@FXML TableColumn<Oeuvre, String> tabType;
	@FXML TableColumn<Oeuvre, String> tabTitle;
	@FXML TableColumn<Oeuvre, String> tabDesc;
	@FXML TableColumn<Oeuvre, Number> tabAv;
	@FXML TableColumn<Oeuvre, Number> tabTotal;
	@FXML TableColumn<Oeuvre, Number> tabPrix;
	@FXML TableColumn<Oeuvre, Number> tabNbResa;
	@FXML TableColumn<Oeuvre, String> tabEditor;
	@FXML TableColumn<Oeuvre, Date> tabDateE;
	@FXML TableColumn<Oeuvre, Date> tabDateS;
	
	public void initialize(URL location, ResourceBundle resources){
		
		if (location.equals(getClass().getClassLoader().getResource("view/oeuvre/OeuvreView.fxml"))) {
			tabId.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("id"));
			tabType.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("type"));
			tabTitle.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("titre"));
			tabDesc.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("description"));
			tabPrix.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("prix"));
			tabAv.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbExemplairesDispo"));
			tabTotal.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbExemplairesTotal"));
			tabNbResa.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbResa"));
			tabEditor.setCellValueFactory(new PropertyValueFactory<Oeuvre,String>("editeur"));
			tabDateE.setCellValueFactory(new PropertyValueFactory<Oeuvre, Date>("dateEdition"));
			tabDateS.setCellValueFactory(new PropertyValueFactory<Oeuvre, Date>("dateArchivage"));
		
			tabType.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabTitle.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabDesc.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabPrix.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabAv.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabTotal.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabAv.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabNbResa.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabEditor.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			
			resultO.setText("aucune action effectuée !");
			resultO.setTextFill(Color.BLUE);
			
			getListArtworks();
		}	
		
		if (location.equals(getClass().getClassLoader().getResource("view/oeuvre/formAddOB.fxml"))) {
			getListAuthorsSelect();
            	
		}
	}
	
	/*
	 * List all oeuvres in tableView
	 */
	
	private void getListArtworks() {
		tabViewO.setItems(gestionOeuvre.listerOeuvres());
		
	}
	
	
	/*
	 * List all authors for book add 
	 */
	
	public void getListAuthorsSelect()
	{
		selectA.setItems(gestionOeuvre.listerAuteursDispo());
	}
	
	/*
	 * Close forms like Add
	 */
	
	@FXML
    private void fermerVue(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    } 
	
	/*
	 * Refresh tableview
	 */
	
	@FXML
	public void actualiserListe () {
		getListArtworks();
		resultO.setText("actualisation terminée !");
		resultO.setTextFill(Color.BLUE);
	}
	
	/*
	 * Search by title
	 * 
	 */
	
	@FXML
	public void trouverOeuvre () {
		ObservableList<Oeuvre> list = gestionOeuvre.trouverOeuvre(searchOeuvre.getText());
		LOG.fine(searchOeuvre.getText());
		tabViewO.setItems(list);
		resultO.setText("nombres d'éléments trouvé(s) : " + list.size());
		resultO.setTextFill(Color.BLUE);
	}
    
    /*
     * Add magazine
     */
	
	@FXML
    public void ajoutFormOM(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/oeuvre/formAddOM.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouveau magazine");
            stage.getIcons().add(new Image("images/open-book.png"));
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    @FXML
	private void ajouterOeuvreMagazine(ActionEvent event) {	
		try {
			if (verifierChamps()) {
				//get values from form
				String titre = title.getText();
				String description = 	desc.getText();
				java.sql.Date dateEdition = java.sql.Date.valueOf(dateE.getValue());
				Double prix = 	Double.parseDouble(price.getText());
				String editeur = editor.getText();
				String numero = num.getText();
				int periodicite = Integer.parseInt(period.getText());
				String type = "Magazine";
				
				//save data in Gestion Oeuvre
				gestionOeuvre.ajouterMagazine(type,titre,description,prix, editeur,dateEdition, numero, periodicite);
				result.setText("Le magazine a été ajouté !");
				result.setTextFill(Color.GREEN);
	        } else {
	        	result.setText("  Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
    	} catch(Exception e) {
			result.setText(" Impossible d'ajouter le magazine ! ");
			result.setTextFill(Color.RED);
		}
	}
    
    /*
     * Add books
     */
    
    @FXML
    public void ajoutFormOB(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/oeuvre/formAddOB.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouveau livre");
            stage.getIcons().add(new Image("images/open-book.png"));
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    @FXML
	private void ajouterOeuvreLivre(ActionEvent event) {
		try {
			if (verifierChamps()) {
				//get values from form
				String titre = title.getText();
				String description = 	desc.getText();
				java.sql.Date dateEdition = java.sql.Date.valueOf(dateE.getValue());
				Double prix = 	Double.parseDouble(price.getText());
				String editeur = editor.getText();
				String resume = resum.getText();
				String type = "Livre";
				Auteur auteur = (Auteur) selectA.getSelectionModel().getSelectedItem();
				
				//save data in Gestion Oeuvre
				if (selectA.getSelectionModel().getSelectedItem() == null ) {
					gestionOeuvre.ajouterLivre(type,titre,description,prix, editeur,dateEdition, resume);
				} else {
					gestionOeuvre.ajouterLivreAuteur(type,titre,description,prix, editeur,dateEdition, resume, auteur);
				}
				result.setText("Le livre a été ajouté !");
				result.setTextFill(Color.GREEN);
	        } else {
	        	result.setText("Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
    	} catch(Exception e) {
			result.setText(" Impossible d'ajouter le livre ! ");
			result.setTextFill(Color.RED);
		}
	}
    
    /*
     * Remove Artwork
     */
    
    @FXML
    public void supprimerOeuvre(ActionEvent event) {
    	if (tabViewO.getSelectionModel().getSelectedItem() == null) {
        	resultO.setText("veuillez sélectionner une oeuvre à supprimer avant !");
			resultO.setTextFill(Color.RED);
		} else {
			Oeuvre oeuvre = tabViewO.getSelectionModel().getSelectedItem();
			int oeuvreID = oeuvre.getId();
			resultO.setText("l'oeuvre avec l'ID " + oeuvreID + " a été supprimé !");
			resultO.setTextFill(Color.GREEN);
			gestionOeuvre.supprimerOeuvre(oeuvreID);
			getListArtworks();
		}
    };
    
    /*
     * Set dateArchivage from Oeuvre
     */
    
    @FXML
    public void archiverOeuvre(ActionEvent event) throws IOException, SQLException {
    	if (tabViewO.getSelectionModel().getSelectedItem() == null) {
        	resultO.setText("veuillez sélectionner une oeuvre à archiver avant !");
			resultO.setTextFill(Color.RED);
		} else {
			Oeuvre oeuvre = tabViewO.getSelectionModel().getSelectedItem();
			int oeuvreID = oeuvre.getId();
			resultO.setText("l'oeuvre avec l'ID " + oeuvreID + " a été archivé !");
			resultO.setTextFill(Color.GREEN);
			gestionOeuvre.archiverOeuvre(oeuvreID);
			getListArtworks();
		}
		
    };
    
    /*
     * check fields
     */
    
    private boolean verifierChamps() {

        boolean validTextFields = true;

        if (title.getText().isEmpty()) {
            validTextFields = false;
            title.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        if (desc.getText().isEmpty()) {
            validTextFields = false;
            desc.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        if (editor.getText().isEmpty()) {
            validTextFields = false;
            editor.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        if (price.getText().isEmpty()) {
            validTextFields = false;
            price.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        
        if (dateE.getValue() == null) {
            validTextFields = false;
            dateE.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-color: #B22222;");
        }

        return validTextFields;
    }

}
