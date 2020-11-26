package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table( name = "EXEMPLAIRE" ) // Apparemment ça sert à rien sauf si on veut que la table ait un nom différent de la classe
public class Exemplaire {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_exemplaire")
    private Integer id;
	
	@Column(name = "etat", nullable=false)	
	private String etat;

	@Column(name = "statut", nullable=false)	
	private String statut;
	
	@Column(name = "titre", nullable=false)	
	private String titre;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_archivage")
	protected Date dateArchivage;
	
	@OneToMany(mappedBy = "exemplaire", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oeuvre")
    private Oeuvre oeuvre = new Oeuvre ();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public Date getDateArchivage() {
		return dateArchivage;
	}

	public void setDateArchivage(Date dateArchivage) {
		this.dateArchivage = dateArchivage;
	}

	public Exemplaire Exemplaire(Oeuvre oeuvre, String etat) {
		throw new UnsupportedOperationException();
	}

	public void supprimerExemplaire(Exemplaire exemplaire) {
		throw new UnsupportedOperationException();
	}
	

	public Exemplaire(Oeuvre oeuvre, String etat) {
		super();
		this.oeuvre = oeuvre;
		this.etat = etat;
		this.statut = "Disponible";
		this.titre = oeuvre.getTitre();
	}

	@Override
	public String toString() {
		//return "Exemplaire [id=" + id + ", etat=" + etat + ", emprunts=" + emprunts + ", titre=" + titre + "]";
		return id + ", " + titre;
	}
	
	
}
