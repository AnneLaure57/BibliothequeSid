package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.*;

public class Emprunt {

	private Date dateEmprunt;
	private String statut;
	private Date dateRetour;
	private int NbEmprunt;

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public int getNbEmprunt() {
		return NbEmprunt;
	}

	public void setNbEmprunt(int nbEmprunt) {
		NbEmprunt = nbEmprunt;
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
