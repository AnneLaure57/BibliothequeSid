package fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.Gestion_Auteur;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Auteur;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Livre;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IHM_Auteur implements Initializable{
	
	/*
	 *  Attributs
	 */
	
	private static final Logger LOG = Logger.getLogger(Gestion_Auteur.class.getName());
	
	private Gestion_Auteur gestionAuteur = new Gestion_Auteur();
	
	@FXML
	private Parent root;
	
	@FXML private Button submit;
	@FXML private Button cancel;
	@FXML private Button actualize;
	
	@FXML private ComboBox select;
	@FXML private ComboBox selectL;
	
	@FXML private Label result;
	@FXML private Label resultA;
	
	@FXML private TextField lastname;
	@FXML private TextField firstname;
	@FXML private TextField searchAuteur;
	
	@FXML TableView<Auteur> tabViewA;
	@FXML TableColumn<Auteur, Number> tabIdA;
	@FXML TableColumn<Auteur, String> tabNameA;
	@FXML TableColumn<Auteur, String> tabFNameA;
	@FXML TableColumn<Auteur, List<Livre>> tabO;
	@FXML TableColumn<Auteur, Date> tabDateS;
	
	@Override
   	public void initialize(URL location, ResourceBundle resources){
	
		if (location.equals(getClass().getClassLoader().getResource("view/auteur/AuteurView.fxml"))) {
			tabIdA.setCellValueFactory(new PropertyValueFactory<Auteur, Number>("id"));
			tabNameA.setCellValueFactory(new PropertyValueFactory<Auteur, String>("nom"));
			tabFNameA.setCellValueFactory(new PropertyValueFactory<Auteur, String>("prenom"));
			tabO.setCellValueFactory(new PropertyValueFactory<Auteur, List<Livre>>("livres"));
			tabDateS.setCellValueFactory(new PropertyValueFactory<Auteur, Date>("dateArchivage"));
		
			tabNameA.setCellFactory(TextFieldTableCell.<Auteur>forTableColumn());
			tabFNameA.setCellFactory(TextFieldTableCell.<Auteur>forTableColumn());
			
			/*tabO.setCellFactory(col -> new TableCell<Auteur, List<Livre>>() {
				@Override
			    public void updateItem(List<Livre> livres, boolean empty) {
			        super.updateItem(livres, empty);
			    }
			});*/
			
			resultA.setText("aucune action effectuée !");
			resultA.setTextFill(Color.BLUE);
			
			getListAuthors();
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/auteur/formAddA.fxml"))) {
			result.setText("Aucune modification enregistrée !");
			result.setTextFill(Color.BLUE);
            getListBooks();
            	
		}
		
		if (location.equals(getClass().getClassLoader().getResource("view/auteur/formAddAO.fxml"))) {
			result.setText("Aucune modification enregistrée !");
			result.setTextFill(Color.BLUE);
			getListAuthorsSelect();
            getListBooks();
            	
		}
			
		if (location.equals(getClass().getClassLoader().getResource("view/auteur/formUpdA.fxml"))) {
			
            result.setText("Aucune modification enregistrée !");
            result.setTextFill(Color.BLUE);
            getListAuthorsSelect();
            	
		}
		
	}

	@FXML
    private void fermerVue(){
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }    
	
	/*
	 * List all authors in tableView
	 */
	
	public void getListAuthors()
	{
		tabViewA.setItems(gestionAuteur.listerAuteurs());
	}
	
	/*
	 * List all books in tableView
	 */
	
	public void getListBooks()
	{
		selectL.setItems(gestionAuteur.listerLivres());
	}
	
	/*
	 * Get the list of autors inside select for modForm
	 */
	
	public void getListAuthorsSelect()
	{
		select.setItems(gestionAuteur.listerAuteursDispo());
	}
	
	/*
	 * Find a usager by lastname/firstname research
	 */
	
	@FXML
	public void trouverAuteur () {
		ObservableList<Auteur> list = gestionAuteur.trouverAuteur(searchAuteur.getText());
		LOG.fine(searchAuteur.getText());
		tabViewA.setItems(list);
		resultA.setText("nombres d'éléments trouvé(s) : " + list.size());
	}
	
	@FXML
	public void selectAuteur() {
		if (select.getSelectionModel().getSelectedItem() == null) {
        	result.setText("Veuillez selectionner un auteur à modifier avant !");
			result.setTextFill(Color.RED);
		} else {
			Auteur auteur = (Auteur) select.getSelectionModel().getSelectedItem();
			int usagerID = auteur.getId();
			//auto complement fields
			Auteur modauteur = gestionAuteur.trouverAuteur(usagerID);
			lastname.setText(modauteur.getNom());
			firstname.setText(modauteur.getPrenom());
		}
	}
	
	@FXML
	public void actualiserListe () {
		getListAuthors();
		resultA.setText("actualisation terminée !");
		resultA.setTextFill(Color.BLUE);
	}
	
	@FXML
	public void ajoutFormA(ActionEvent event) {
        try {
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/auteur/formAddA.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter un nouvel Auteur");
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
	private void ajouterAuteur(ActionEvent event) {
		try {
			if (verifierChamps()) {
				//get values from form
				LOG.fine(lastname.getText() + ", " + firstname.getText());
				String nom = lastname.getText();
				String prenom = 	firstname.getText();
				Livre livre = (Livre) selectL.getSelectionModel().getSelectedItem();
				
				//save data in Gestion Auteur
				if (selectL.getSelectionModel().getSelectedItem() == null ) {
					gestionAuteur.ajouterAuteur(nom,prenom);
				} else {
					gestionAuteur.ajouterAuteurLivre(nom,prenom,livre);
				}
				result.setText("L'auteur a été ajouté !");
				result.setTextFill(Color.GREEN);
	        } else {
	        	result.setText("Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
    	} catch(Exception e) {
			result.setText(" Impossible d'ajouter l'auteur ! ");
			result.setTextFill(Color.RED);
		}
	}
    
    @FXML
	public void ajoutFormAO(ActionEvent event) {
        try {
        	//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/formAddU.fxml"));
        	//Parent part = fxmlLoader.load();
        	Parent part = FXMLLoader.load(getClass().getClassLoader().getResource("view/auteur/formAddAO.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajouter une nouvelle oeuvre pour un auteur");
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
    private void lierAuteurLivre(ActionEvent event) {
    	try {
    		if (selectL.getSelectionModel().getSelectedItem() == null || select.getSelectionModel().getSelectedItem() == null) {
                // one or more of the text fields are empty
        		result.setText("Veuillez remplir les champs manquants !");
        		result.setTextFill(Color.RED);
                return;
            }
        	Auteur auteur = (Auteur) select.getSelectionModel().getSelectedItem();
    		Livre livre = (Livre) selectL.getSelectionModel().getSelectedItem();
    		//save data in Gestion Auteur
    		gestionAuteur.lierAuteurLivre(auteur,livre);
    		result.setText("Le livre a été associé à l'auteur !");
    		result.setTextFill(Color.GREEN);
    	} catch(Exception e) {
			result.setText(" Impossible de lier le livre à l'auteur ! ");
			result.setTextFill(Color.RED);
		}
    	
	}

    @FXML
    public void modFormA(ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/auteur/formUpdA.fxml"));
			root = loader.load();
			IHM_Auteur controller = loader.getController();
	        Stage stage = new Stage();
	        stage.setTitle("Modifier un auteur");
	        stage.getIcons().add(new Image("images/open-book.png"));
	        stage.setScene(new Scene(root));  
	        stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    };

	@FXML
	private void modifierAuteur(ActionEvent event) {
		try {
			if (verifierChamps()) {
				//get values from form
				Auteur us = (Auteur) select.getSelectionModel().getSelectedItem();
				int id = us.getId();
				String nom = lastname.getText();
				String prenom = 	firstname.getText();
				
				//save data in Gestion Auteur
				gestionAuteur.modifierAuteur(id,nom,prenom);
				result.setText("L'auteur a été modifié !");
				result.setTextFill(Color.GREEN);
	        } else {
	        	result.setText("Veuillez remplir tout les champs !");
				result.setTextFill(Color.RED);
	        }
    	} catch(Exception e) {
			result.setText(" Impossible de modifier l'auteur ! ");
			result.setTextFill(Color.RED);
		}
	}
	
	/*
	 *  delete Usager
	 */
    
    @FXML
    public void supprimerAuteur(ActionEvent event) throws IOException, SQLException {
        if (tabViewA.getSelectionModel().getSelectedItem() == null) {
        	resultA.setText("veuillez sélectionner un auteur à supprimer avant !");
			resultA.setTextFill(Color.RED);
		} else {
			Auteur auteur = tabViewA.getSelectionModel().getSelectedItem();
			int auteurID = auteur.getId();
			resultA.setText("l'auteur avec l'ID " + auteurID + " a été supprimé !");
			resultA.setTextFill(Color.GREEN);
			gestionAuteur.supprimerAuteur(auteurID);
			getListAuthors();
		}
		
    };
    
    /*
     * 
     *  Archivage
     */
    
    @FXML
    public void archiverAuteur(ActionEvent event) throws IOException, SQLException {
        if (tabViewA.getSelectionModel().getSelectedItem() == null) {
        	resultA.setText("veuillez sélectionner un usager à archiver avant !");
			resultA.setTextFill(Color.RED);
		} else {
			Auteur auteur = tabViewA.getSelectionModel().getSelectedItem();
			int auteurID = auteur.getId();
			resultA.setText("l'usager avec l'ID " + auteurID + " a été supprimé !");
			resultA.setTextFill(Color.GREEN);
			gestionAuteur.archiverAuteur(auteurID);
			getListAuthors();
		}
		
    };
    
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

        return validTextFields;
    }
    
}
