package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table( name = "AUTEUR" ) 
public class Auteur {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_auteur")
    private Integer id;
	
	@ManyToMany(mappedBy = "auteurs", cascade = { CascadeType.MERGE, CascadeType.REMOVE})
	private List<Livre> livres = new ArrayList<>();
	
	@Column(name = "nom", nullable=false) // NOT NULL
	private String nom;
	
	@Column(name = "prenom", nullable=false) // NOT NULL
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_archivage")
	private Date dateArchivage;
	
	public List<Livre> addLivres(Livre livre) {
		livres.add(livre);
        livre.getAuteurs().add(this);
		return livres;
    }
	
	public Auteur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Auteur(String nom, String prenom, Livre livre) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.livres = addLivres(livre);
	}
	
	public Auteur(Integer id, List<Livre> livres, String nom, String prenom) {
		super();
		this.id = id;
		this.livres = livres;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	@Override
	public String toString() {
		return "id :" + id + ", " + nom + " - " + prenom;
	}
}
