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
	
	@Column(name = "telephone", nullable=false, length=10)
	private String telephone;
	
	@Column(name = "mail", nullable=false)
	private String mail;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_creation")
	private Date dateCreation;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_naissance")
	private Date dateNaissance;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_archivage")
	private Date dateArchivage;

	@OneToMany(mappedBy = "usager", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts = new ArrayList<>();

    @OneToMany(mappedBy = "usager", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

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
