package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.*;

public class Emprunt {

	private int dateEmprunt;
	private int statut;
	private int dateRetour;
	private int NbEmprunt;
	private int duree;

	public void getDateEmprunt() {
		throw new UnsupportedOperationException();
	}

	public void setDateEmprunt(int dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public void getStatut() {
		throw new UnsupportedOperationException();
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public void getDateRetour() {
		throw new UnsupportedOperationException();
	}

	public void setDateRetour(int dateRetour) {
		this.dateRetour = dateRetour;
	}

	public void getNbEmprunt() {
		throw new UnsupportedOperationException();
	}

	public void setNbEmprunt(int NbEmprunt) {
		throw new UnsupportedOperationException();
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
}
