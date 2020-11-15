package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Pour associer la classe à une table dans la base
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "AUTEUR" ) 
public class Auteur {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_auteur")
    private Integer id;
	
	@ManyToMany(mappedBy = "auteurs", cascade = CascadeType.MERGE)
	private List<Livre> livres = new ArrayList<>();
	
	@Column(name = "nom", nullable=false) // NOT NULL
	private String nom;
	
	@Column(name = "prenom", nullable=false) // NOT NULL
	private String prenom;
	
	public Auteur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
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
	
	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
}
