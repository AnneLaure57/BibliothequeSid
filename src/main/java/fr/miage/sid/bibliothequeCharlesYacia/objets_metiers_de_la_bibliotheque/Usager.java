package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity // Pour associer la classe à une table dans la base
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "USAGER")
@NamedQuery(name="findUsagerByNom", query="SELECT u FROM Usager u WHERE u.nom = :nom")
public class Usager {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_usager")
    private int id;
	
	@Column(name = "nom", nullable=false) // NOT NULL
	private String nom;
	
	@Column(name = "prenom", nullable=false) // NOT NULL
	private String prenom;
	
	@Column(name = "adresse", nullable=false) // NOT NULL
	private String adresse;
	
	@Column(name = "code_postal", nullable=false, length=5)
	private int codepostal;
	
	@Column(name = "ville", nullable=false)
	private String ville;
	
	@Column(name = "telephone", length=10)
	private String telephone;
	
	@Column(name = "mail")
	private String mail;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_creation")
	private Date dateCreation;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_naissance")
	private Date dateNaissance;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_suppression")
	private Date dateSuppression;

	@OneToMany(mappedBy = "usager", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts = new ArrayList<>();

    @OneToMany(mappedBy = "usager", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
	
    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }
    
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(int codepostal) {
		this.codepostal = codepostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Date dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Usager e_identification(String nom) {
		throw new UnsupportedOperationException();
	}

	public void supprimerUsager(Usager usager) {
		throw new UnsupportedOperationException();
	}

	public Usager(String nom, String prenom, String adresse, int codepostal, String ville, String telephone, String mail,
			 Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codepostal = codepostal;
		this.ville = ville;
		this.telephone = telephone;
		this.mail = mail;
		this.dateCreation = new Date();
		this.dateNaissance = dateNaissance;
		
	}

	@Override
	public String toString() {
		return "id :" + id + ", " + nom + " - " + prenom;
	}

}
