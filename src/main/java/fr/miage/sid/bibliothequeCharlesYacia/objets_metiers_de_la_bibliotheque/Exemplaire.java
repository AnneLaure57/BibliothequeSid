package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

public class Exemplaire {

	private int id;
	private String etat;

	

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
}
