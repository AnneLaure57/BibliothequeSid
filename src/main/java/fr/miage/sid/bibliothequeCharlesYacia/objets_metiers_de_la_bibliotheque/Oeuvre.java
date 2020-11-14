package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.Date;

public class Oeuvre {

	private String titre;
	private String description;
	private int nbExemplairesDispo;
	private int nbExemplairesTotal;
	private int prix;
	private int nbResa;
	private String editeur;
	private Date dateEdition;

	

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
}
