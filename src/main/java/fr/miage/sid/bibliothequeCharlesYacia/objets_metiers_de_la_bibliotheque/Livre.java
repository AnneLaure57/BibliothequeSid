package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@DiscriminatorValue("Livre")
public class Livre extends Oeuvre {
	
	@Column(name = "resume")
	private String resume;
	
	@ManyToMany
	@JoinTable(
            name = "LIVREAUTEUR",
            joinColumns = {@JoinColumn(name = "id_livre", referencedColumnName="id_oeuvre")},
            inverseJoinColumns = {@JoinColumn(name = "id_auteur", referencedColumnName="id_auteur")}
    )
	private List<Auteur> auteurs = new ArrayList<>();
	
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Livre(String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, int prix,
			String editeur, Date dateEdition) {
		super(titre, description, nbExemplairesDispo, nbExemplairesTotal, prix, editeur, dateEdition);
	}

	public Livre(String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, int prix,
			String editeur, Date dateEdition, String resume) {
		super(titre, description, nbExemplairesDispo, nbExemplairesTotal, prix, editeur, dateEdition);
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "Livre [resume=" + resume + "]";
	}

	
}
