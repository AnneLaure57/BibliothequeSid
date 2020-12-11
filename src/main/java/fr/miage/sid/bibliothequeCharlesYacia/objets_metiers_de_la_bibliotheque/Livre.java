package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Livre")
public class Livre extends Oeuvre {
	
	//TODO revoir héritage
	//TODO voir relation avec oeuvre
	
	@Column(name = "resume")
	private String resume;
	
	// remove type ALL, to avoid exception merge problems
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
            name = "LIVREAUTEUR",
            joinColumns = {@JoinColumn(name = "id_livre", referencedColumnName="id_oeuvre")},
            inverseJoinColumns = {@JoinColumn(name = "id_auteur", referencedColumnName="id_auteur")}
    )
	private List<Auteur> auteurs = new ArrayList<>();

	public Livre(String type, String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, Double prix, String editeur, Date dateEdition) {
		//super(titre, description, nbExemplairesDispo, nbExemplairesTotal, prix, editeur, dateEdition);
		super(titre, description, prix, editeur, dateEdition);
	}

	public Livre(String type, String titre, String description, Double prix, String editeur, Date dateEdition, String resume) {
		super(type,titre, description, prix, editeur, dateEdition);
		this.resume = resume;
	}

	@Override
	public String toString() {
		return titre;
	}
	
	/*public String toString() {
		return "Livre [ auteurs=" + auteurs + ", id=" + id + ", type=" + type + ", titre=" + titre
				+ ", description=" + description + ", nbExemplairesDispo=" + nbExemplairesDispo
				+ ", nbExemplairesTotal=" + nbExemplairesTotal + ", prix=" + prix + ", nbResa=" + nbResa + ", editeur="
				+ editeur + ", dateEdition=" + dateEdition + ", dateSuppression=" + dateArchivage + "resume=" + resume + "]";
	}*/
	
}
