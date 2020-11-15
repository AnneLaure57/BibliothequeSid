package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.ArrayList;
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
    private Integer id;
	
	@Column(nullable=false)	
	private String etat;

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

	public Emprunt emprunter(Usager u, Oeuvre o, int DateJour) {
		throw new UnsupportedOperationException();
	}

	public Exemplaire e_identification(Oeuvre o) {
		throw new UnsupportedOperationException();
	}

	public Exemplaire e_identification(int numero) {
		throw new UnsupportedOperationException();
	}

	public void ajouterExemplaire(String titre) {
		throw new UnsupportedOperationException();
	}

	public Exemplaire Exemplaire(Oeuvre oeuvre, String etat) {
		throw new UnsupportedOperationException();
	}

	public void supprimerExemplaire(Exemplaire exemplaire) {
		throw new UnsupportedOperationException();
	}
	
	

	public Exemplaire(Oeuvre oeuvre) {
		super();
		this.oeuvre = oeuvre;
		this.etat = "actif";
	}

	public Exemplaire() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Exemplaire [id=" + id + ", etat=" + etat + ", emprunts=" + emprunts + ", oeuvre=" + oeuvre + "]";
	}
	
	
}
