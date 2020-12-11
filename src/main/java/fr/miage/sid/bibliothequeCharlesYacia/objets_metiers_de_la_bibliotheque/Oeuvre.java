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
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Pour associer la classe à une table dans la base
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "OEUVRE" , uniqueConstraints=@UniqueConstraint(columnNames = {"titre", "editeur", "date_edition"})) 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="oeuvre_type", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name="findOeuvreByTitre", query="SELECT o FROM Oeuvre o WHERE o.titre = :titre")
public class Oeuvre {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_oeuvre")
    protected int id;
	
	@Column(name = "type")
	protected String type;
	
	@Column(name = "titre", nullable=false)
	protected String titre;
	
	@Column(name = "description")
	protected String description;
	
	@Column(name = "nb_exemp_dispo", nullable=false)
	protected int nbExemplairesDispo;
	
	@Column(name = "nb_exemp_total", nullable=false)
	protected int nbExemplairesTotal;
	
	@Column(name = "prix")
	protected Double prix;
	
	@Column(name = "nb_reservation", nullable=false)
	protected int nbResa;
	
	@Column(name = "editeur")
	protected String editeur;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_edition")
	protected Date dateEdition;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_archivage")
	protected Date dateArchivage;

	@OneToMany(mappedBy = "oeuvre", cascade = CascadeType.ALL)
	protected List<Emprunt> emprunt = new ArrayList<>();
	
	@OneToMany(mappedBy = "oeuvre", cascade = CascadeType.ALL)
	protected List<Reservation> reservations = new ArrayList<>();
	
	@OneToMany(mappedBy = "oeuvre", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<Exemplaire> exemplaires = new ArrayList<>();

	public Oeuvre(String titre, String description, Double prix, String editeur, Date dateEdition) {
		super();
		this.titre = titre;
		this.description = description;
		this.nbExemplairesDispo = 0;
		this.nbExemplairesTotal = 0;
		this.prix = prix;
		this.nbResa = 0;
		this.editeur = editeur;
		this.dateEdition = dateEdition;
	}
	
	public Oeuvre(String type, String titre, String description, Double prix, String editeur, Date dateEdition) {
		super();
		this.type = type;
		this.titre = titre;
		this.description = description;
		this.nbExemplairesDispo = 0;
		this.nbExemplairesTotal = 0;
		this.prix = prix;
		this.nbResa = 0;
		this.editeur = editeur;
		this.dateEdition = dateEdition;
	}

	@Override
	public String toString() {
		return "id=" + id + "titre=" + titre ;
	}
	/*public String toString() {
		return "Oeuvre [id=" + id + ", type=" + type + ", titre=" + titre + ", description=" + description
				+ ", nbExemplairesDispo=" + nbExemplairesDispo + ", nbExemplairesTotal=" + nbExemplairesTotal
				+ ", prix=" + prix + ", nbResa=" + nbResa + ", editeur=" + editeur + ", dateEdition=" + dateEdition
				+ ", dateSuppression=" + dateArchivage + ", emprunt=" + emprunt + ", reservations=" + reservations
				+ ", exemplaires=" + exemplaires + "]";
	}*/
}
