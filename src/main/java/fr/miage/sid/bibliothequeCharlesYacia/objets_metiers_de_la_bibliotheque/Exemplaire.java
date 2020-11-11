package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

public class Exemplaire {

	private int numero;
	private int etat;

	public void getEtat() {
		throw new UnsupportedOperationException();
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public void getId() {
		throw new UnsupportedOperationException();
	}

	public void setId(int id) {
		throw new UnsupportedOperationException();
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
}
