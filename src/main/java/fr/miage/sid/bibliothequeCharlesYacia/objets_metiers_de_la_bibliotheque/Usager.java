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
@Table( name = "USAGER" ) // Apparemment ça sert à rien sauf si on veut que la table ait un nom différent de la classe
@NamedQuery(name="findUsagerByNom", query="SELECT u FROM Usager u WHERE u.nom = :nom")
public class Usager {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable=false) // NOT NULL
	private String nom;
	
	@Column(nullable=false) // NOT NULL
	private String prenom;
	
	private String adresse;
	
	@Column(name = "code_postal")
	private int codepostal;
	
	private String ville;
	
	private String telephone;
	
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

	public Usager(String nom, String prenom, String adresse, int codepostal, String ville, String telephone,
			 Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codepostal = codepostal;
		this.ville = ville;
		this.telephone = telephone;
		this.dateCreation = new Date();
		this.dateNaissance = dateNaissance;
		
	}

	public Usager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Usager [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codepostal="
				+ codepostal + ", ville=" + ville + ", telephone=" + telephone + ", dateCreation=" + dateCreation
				+ ", dateNaissance=" + dateNaissance + ", dateSuppression=" + dateSuppression + "]";
	}
	
	
}
