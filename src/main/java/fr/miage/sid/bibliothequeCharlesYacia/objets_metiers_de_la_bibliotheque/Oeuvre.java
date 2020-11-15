package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table( name = "OEUVRE" ) // Apparemment ça sert à rien sauf si on veut que la table ait un nom différent de la classe
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="oeuvre_type", discriminatorType = DiscriminatorType.STRING)
//@NamedQuery(name="findOeuvreByTitre", query="SELECT o FROM OEUVRE o WHERE o.titre = :titre")
public class Oeuvre {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_oeuvre")
    private Integer id;
	
	@Column(name = "titre", nullable=false)
	private String titre;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "nb_exemp_dispo")
	private int nbExemplairesDispo;
	
	@Column(name = "nb_exemp_total")
	private int nbExemplairesTotal;
	
	@Column(name = "prix")
	private int prix;
	
	@Column(name = "nb_reservation")
	private int nbResa;
	
	@Column(name = "editeur")
	private String editeur;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_edition")
	private Date dateEdition;

	@OneToMany(mappedBy = "oeuvre", cascade = CascadeType.ALL)
    private List<Emprunt> emprunt = new ArrayList<>();
	
	@OneToMany(mappedBy = "oeuvre", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
	
	@OneToMany(mappedBy = "oeuvre", cascade = CascadeType.ALL)
    private List<Exemplaire> exemplaires = new ArrayList<>();
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbExemplairesDispo() {
		return nbExemplairesDispo;
	}

	public void setNbExemplairesDispo(int nbExemplairesDispo) {
		this.nbExemplairesDispo = nbExemplairesDispo;
	}

	public int getNbExemplairesTotal() {
		return nbExemplairesTotal;
	}

	public void setNbExemplairesTotal(int nbExemplairesTotal) {
		this.nbExemplairesTotal = nbExemplairesTotal;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getNbResa() {
		return nbResa;
	}

	public void setNbResa(int nbResa) {
		this.nbResa = nbResa;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public Date getDateEdition() {
		return dateEdition;
	}

	public void setDateEdition(Date dateEdition) {
		this.dateEdition = dateEdition;
	}

	public void findOeuvre() {
		throw new UnsupportedOperationException();
	}

	public Oeuvre e_identification(String titre) {
		throw new UnsupportedOperationException();
	}

	public void supprimerOeuvre(Oeuvre oeuvre) {
		throw new UnsupportedOperationException();
	}
	
	
	public Oeuvre(String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, int prix, String editeur, Date dateEdition) {
		super();
		this.titre = titre;
		this.description = description;
		this.nbExemplairesDispo = nbExemplairesDispo;
		this.nbExemplairesTotal = nbExemplairesTotal;
		this.prix = prix;
		this.nbResa = 0;
		this.editeur = editeur;
		this.dateEdition = dateEdition;
	}
	
	
	public Oeuvre() {
		super();
	}

	@Override
	public String toString() {
		return "Oeuvre [id=" + id + ", titre=" + titre + ", description=" + description + ", nbExemplairesDispo="
				+ nbExemplairesDispo + ", nbExemplairesTotal=" + nbExemplairesTotal + ", prix=" + prix + ", nbResa="
				+ nbResa + ", editeur=" + editeur + ", dateEdition=" + dateEdition + "]";
	}
	
	
}
