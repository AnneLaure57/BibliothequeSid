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
@Table( name = "EMPRUNT" ) // Apparemment ça sert à rien sauf si on veut que la table ait un nom différent de la classe
public class Emprunt {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_emprunt")
    private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_emprunt",nullable=false)
	private Date dateEmprunt;
	
	@Column(name = "statut",nullable=false)	
	private String statut;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_retour",nullable=false)
	private Date dateRetour;
	
	@Column(name = "nb_emprunt")
	private int NbEmprunt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usager")
    private Usager usager = new Usager ();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oeuvre")
    private Oeuvre oeuvre = new Oeuvre ();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire = new Exemplaire ();
	
	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public int getNbEmprunt() {
		return NbEmprunt;
	}

	public void setNbEmprunt(int nbEmprunt) {
		NbEmprunt = nbEmprunt;
	}

	public Emprunt emprunt(Usager u, Oeuvre o, Exemplaire e, Date dateJour) {
		throw new UnsupportedOperationException();
	}

	public void rendreExemplaire(Usager usager, Oeuvre oeuvre, Exemplaire exemplaire, Date dateJ) {
		throw new UnsupportedOperationException();
	}

	public Emprunt e_identification(Usager usager) {
		throw new UnsupportedOperationException();
	}

	public void supprimerEmprunt(Emprunt emprunt) {
		throw new UnsupportedOperationException();
	}
	
	

	public Emprunt(Usager usager, Oeuvre oeuvre, Exemplaire exemplaire) {
		super();
		this.usager = usager;
		this.oeuvre = oeuvre;
		this.exemplaire = exemplaire;
		this.dateEmprunt = new Date();
		this.statut = "en_cours";
		
	}

	@Override
	public String toString() {
		return "Emprunt [id=" + id + ", dateEmprunt=" + dateEmprunt + ", statut=" + statut + ", dateRetour="
				+ dateRetour + ", NbEmprunt=" + NbEmprunt + "]";
	}
	
}
