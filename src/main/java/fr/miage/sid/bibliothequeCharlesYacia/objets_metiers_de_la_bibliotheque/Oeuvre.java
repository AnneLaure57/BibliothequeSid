package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

public class Oeuvre {

	private int titre;
	private int description;
	private int nbExemplairesDispo;
	private int nbExemplairesTotal;
	private int prix;
	private int nbResa;
	private int editeur;
	private int dateEdition;

	public void getTitre() {
		throw new UnsupportedOperationException();
	}

	public void setTitre(int titre) {
		this.titre = titre;
	}

	public void getDescription() {
		throw new UnsupportedOperationException();
	}

	public void setDescription(int description) {
		this.description = description;
	}

	public void getNbExemplaires() {
		throw new UnsupportedOperationException();
	}

	public void setNbExemplaires(int nbExemplaires) {
		throw new UnsupportedOperationException();
	}

	public void getNbExemplairesTotal() {
		throw new UnsupportedOperationException();
	}

	public void setNbExemplairesTotal(int nbExemplairesTotal) {
		this.nbExemplairesTotal = nbExemplairesTotal;
	}

	public void findOeuvre() {
		throw new UnsupportedOperationException();
	}

	public Oeuvre e_identification(String titre) {
		throw new UnsupportedOperationException();
	}

	public void getNbResa() {
		throw new UnsupportedOperationException();
	}

	public void setNbResa(int nbResa) {
		this.nbResa = nbResa;
	}

	public void supprimerOeuvre(Oeuvre oeuvre) {
		throw new UnsupportedOperationException();
	}
}
