package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Oeuvre;
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

public class IHM_Oeuvre implements Initializable{
	
    private static final Logger LOG = Logger.getLogger(Gestion_Oeuvre.class.getName());
    Gestion_Oeuvre gestionOeuvre = new Gestion_Oeuvre();
    
    @FXML private Button submit;
	@FXML private Button cancel;
	@FXML private Button actualize;
	
	@FXML private Label result;
	@FXML private Label resultO;
	
	@FXML private TextField title;
	@FXML private TextField desc;
	@FXML private DatePicker dateE;
	@FXML private TextField price;
	@FXML private TextField editor;
	@FXML private TextField nbTotal;
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
	
	@FXML TableView<Oeuvre> tabViewOB;
	@FXML TableColumn<Oeuvre, Number> tabIdB;
	@FXML TableColumn<Oeuvre, String> tabTypeB;
	@FXML TableColumn<Oeuvre, String> tabTitleB;
	@FXML TableColumn<Oeuvre, String> tabDescB;
	@FXML TableColumn<Oeuvre, Number> tabAvB;
	@FXML TableColumn<Oeuvre, Number> tabTotalB;
	@FXML TableColumn<Oeuvre, Number> tabPrixB;
	@FXML TableColumn<Oeuvre, Number> tabNbResaB;
	@FXML TableColumn<Oeuvre, String> tabEditorB;
	@FXML TableColumn<Oeuvre, Date> tabDateEB;
	@FXML TableColumn<Oeuvre, Date> tabDateSB;
	@FXML TableColumn<Livre, String> tabResum;
	
	@FXML TableView<Oeuvre> tabViewOM;
	@FXML TableColumn<Oeuvre, Number> tabIdM;
	@FXML TableColumn<Oeuvre, String> tabTypeM;
	@FXML TableColumn<Oeuvre, String> tabTitleM;
	@FXML TableColumn<Oeuvre, String> tabDescM;
	@FXML TableColumn<Oeuvre, Number> tabAvM;
	@FXML TableColumn<Oeuvre, Number> tabTotalM;
	@FXML TableColumn<Oeuvre, Number> tabPrixM;
	@FXML TableColumn<Oeuvre, Number> tabNbResaM;
	@FXML TableColumn<Oeuvre, String> tabEditorM;
	@FXML TableColumn<Oeuvre, Date> tabDateEM;
	@FXML TableColumn<Oeuvre, Date> tabDateSM;
	@FXML TableColumn<Livre, Number> tabRef;
	@FXML TableColumn<Livre, Number> tabPer;
	
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
			tabDateS.setCellValueFactory(new PropertyValueFactory<Oeuvre, Date>("dateSuppression"));
		
			tabType.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabTitle.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabDesc.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabPrix.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabAv.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabTotal.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabAv.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabNbResa.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabEditor.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			
			resultO.setText("Aucune action effectuée !");
			resultO.setTextFill(Color.BLUE);
			
			getListOeuvres();
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/oeuvre/listMags.fxml"))) {
			
			tabIdM.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("id"));
			tabTitleM.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("titre"));
			tabDescM.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("description"));
			tabPrixM.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("prix"));
			tabAvM.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbExemplairesDispo"));
			tabTotalM.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbExemplairesTotal"));
			tabNbResaM.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbResa"));
			tabEditorM.setCellValueFactory(new PropertyValueFactory<Oeuvre,String>("editeur"));
			tabDateEM.setCellValueFactory(new PropertyValueFactory<Oeuvre, Date>("dateEdition"));
			tabDateSM.setCellValueFactory(new PropertyValueFactory<Oeuvre, Date>("dateSuppression"));
		
			tabTitleM.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabDescM.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabPrixM.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabAvM.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabTotalM.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabAvM.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabNbResaM.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabEditorM.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			
			getListMags();
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/oeuvre/listBooks.fxml"))) {
			
			//TODO find how display O + L
			
			tabIdB.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("id"));
			tabTitleB.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("titre"));
			tabDescB.setCellValueFactory(new PropertyValueFactory<Oeuvre, String>("description"));
			tabPrixB.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("prix"));
			tabAvB.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbExemplairesDispo"));
			tabTotalB.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbExemplairesTotal"));
			tabNbResaB.setCellValueFactory(new PropertyValueFactory<Oeuvre, Number>("nbResa"));
			tabEditorB.setCellValueFactory(new PropertyValueFactory<Oeuvre,String>("editeur"));
			tabDateEB.setCellValueFactory(new PropertyValueFactory<Oeuvre, Date>("dateEdition"));
			tabDateSB.setCellValueFactory(new PropertyValueFactory<Oeuvre, Date>("dateSuppression"));
		
			tabTitleB.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabDescB.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			tabPrixB.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabAvB.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabTotalB.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabAvB.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabNbResaB.setCellFactory(TextFieldTableCell.<Oeuvre, Number>forTableColumn(new NumberStringConverter()));
			tabEditorB.setCellFactory(TextFieldTableCell.<Oeuvre>forTableColumn());
			
			getListLivres();
		}
			
	}
	
	/*
	 * List all oeuvres in tableView
	 */
	
	private void getListOeuvres() {
		tabViewO.setItems(gestionOeuvre.ListerOeuvres());
		//tabViewO.setItems(gestionOeuvre.ListerMagazines());
		
	}
	
	private void getListMags() {
		tabViewOM.setItems(gestionOeuvre.ListerMagazines());
		
	}
	
	private void getListLivres() {
		tabViewOB.setItems(gestionOeuvre.ListerLivres());
		
	}
	
	/*
	 * Close forms like Add
	 */
	
	@FXML
    private void closeView(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    } 
	
	/*
	 * Refresh tableview
	 */
	
	@FXML
	public void actualizeList () {
		getListOeuvres();
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
	}
	
	/*
     * Add oeuvre V1
     */
	
	@FXML
    public void ajoutFormO(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/oeuvre/formAddO.fxml"));
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
    
    @FXML
	private void ajouterOeuvre(ActionEvent event) {
    	if (! textFieldsValid()) {
            // one or more of the text fields are empty
    		result.setText("Veuillez remplir les champs manquants !");
    		result.setTextFill(Color.RED);
            return;
        }
		LOG.fine(title.getText() + ", " + desc.getText() + ", " + dateE.getValue() + ", " + price.getText() + ", " + editor.getText() + ", " + nbTotal.getText());
		String titre = title.getText();
		String description = 	desc.getText();
		java.sql.Date dateEdition = java.sql.Date.valueOf(dateE.getValue());
		int prix = 	Integer.parseInt(price.getText());
		int nombreTotal = 	Integer.parseInt(nbTotal.getText());
		String editeur = editor.getText();
		
		//save data in Gestion Oeuvre
		//(String titre, String description, int nbExemplairesTotal, int prix, String editeur, Date dateEdition)
		//gestionOeuvre.ajouterOeuvre(titre, description,nombreTotal, nombreTotal,prix, editeur,dateEdition);
		result.setText("L'oeuvre a été ajouté !");
		result.setTextFill(Color.GREEN);
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
    	if (! textFieldsValid()) {
            // one or more of the text fields are empty
    		result.setText("Veuillez remplir les champs manquants !");
    		result.setTextFill(Color.RED);
            return;
        }
		LOG.fine(title.getText() + ", " + desc.getText() + ", " + dateE.getValue() + ", " + price.getText() + ", " + editor.getText() + ", " + nbTotal.getText()
		+ ", " + num.getText() +period.getText());
		String titre = title.getText();
		String description = 	desc.getText();
		java.sql.Date dateEdition = java.sql.Date.valueOf(dateE.getValue());
		int prix = 	Integer.parseInt(price.getText());
		int nombreTotal = 	Integer.parseInt(nbTotal.getText());
		String editeur = editor.getText();
		String numero = num.getText();
		int periodicite = Integer.parseInt(period.getText());
		String type = "Magazine";
		
		//save data in Gestion Oeuvre
		//gestionOeuvre.ajouterOeuvre(type,titre, description,nombreTotal, nombreTotal,prix, editeur,dateEdition);
		//TODO correct FXML Exception (maybe add tab in table view)
		gestionOeuvre.ajouterMagazine(type,titre,description,nombreTotal, nombreTotal,prix, editeur,dateEdition, numero, periodicite);
		result.setText("Le magazine a été ajouté !");
		result.setTextFill(Color.GREEN);
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
    	if (! textFieldsValid()) {
            // one or more of the text fields are empty
    		result.setText("Veuillez remplir les champs manquants !");
    		result.setTextFill(Color.RED);
            return;
        }
		LOG.fine(title.getText() + ", " + desc.getText() + ", " + dateE.getValue() + ", " + price.getText() + ", " + editor.getText() + ", " + nbTotal.getText()
		+ ", " + resum.getText());
		String titre = title.getText();
		String description = 	desc.getText();
		java.sql.Date dateEdition = java.sql.Date.valueOf(dateE.getValue());
		int prix = 	Integer.parseInt(price.getText());
		int nombreTotal = 	Integer.parseInt(nbTotal.getText());
		String editeur = editor.getText();
		String resume = resum.getText();
		String type = "Livre";
		
		//save data in Gestion Oeuvre
		//gestionOeuvre.ajouterOeuvre(type,titre, description,nombreTotal, nombreTotal,prix, editeur,dateEdition);
		//TODO correct FXML Exception (maybe add tab in table view)
		gestionOeuvre.ajouterLivre(type,titre,description,nombreTotal, nombreTotal,prix, editeur,dateEdition, resume);
		result.setText("Le livre a été ajouté !");
		result.setTextFill(Color.GREEN);
	}
    
    /*
     * Remove oeuvre
     */
    
    @FXML
    public void supprimerOeuvre(ActionEvent event) {
    	if (tabViewO.getSelectionModel().getSelectedItem() == null) {
        	resultO.setText("Veuillez sélectionner une oeuvre à supprimer avant !");
			resultO.setTextFill(Color.RED);
		} else {
			Oeuvre oeuvre = tabViewO.getSelectionModel().getSelectedItem();
			int oeuvreID = oeuvre.getId();
			resultO.setText("L'oeuvre avec l'ID " + oeuvreID + " a été supprimé !");
			resultO.setTextFill(Color.GREEN);
			gestionOeuvre.supprimerOeuvre(oeuvreID);
			getListOeuvres();
		}
    };
    
    /*
     * Set dateSuppression from Oeuvre
     */
    
    @FXML
    public void archiverOeuvre(ActionEvent event) throws IOException, SQLException {
    	if (tabViewO.getSelectionModel().getSelectedItem() == null) {
        	resultO.setText("Veuillez sélectionner une oeuvre à archiver avant !");
			resultO.setTextFill(Color.RED);
		} else {
			Oeuvre oeuvre = tabViewO.getSelectionModel().getSelectedItem();
			int oeuvreID = oeuvre.getId();
			resultO.setText("L'oeuvre avec l'ID " + oeuvreID + " a été archivé !");
			resultO.setTextFill(Color.GREEN);
			gestionOeuvre.archiverOeuvre(oeuvreID);
			getListOeuvres();
		}
		
    };
    
    private boolean textFieldsValid() {

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
        
        if (nbTotal.getText().isEmpty()) {
            validTextFields = false;
            nbTotal.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
        
        if (dateE.getValue() == null) {
            validTextFields = false;
            dateE.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }

        return validTextFields;
    }

}
