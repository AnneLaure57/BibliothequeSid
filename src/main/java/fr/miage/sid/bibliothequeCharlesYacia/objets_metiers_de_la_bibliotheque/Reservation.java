package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.*;

public class Reservation {

	private int dateReservation;
	private int statut;
	private int dateAnnulation;

	public void getDateReservation() {
		throw new UnsupportedOperationException();
	}

	public void setDateReservation(int dateReservation) {
		this.dateReservation = dateReservation;
	}

	public void getStatut() {
		throw new UnsupportedOperationException();
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public Reservation(Usager u, Oeuvre o, Date DateJour) {
		throw new UnsupportedOperationException();
	}

	public Reservation e_identification(Usager usager, Oeuvre oeuvre) {
		throw new UnsupportedOperationException();
	}

	public void annulerReservation(Reservation reservation, Date dateJ) {
		throw new UnsupportedOperationException();
	}

	public Reservation e_identification(Usager usager) {
		throw new UnsupportedOperationException();
	}

	public void supprimerReservation(Reservation reservation) {
		throw new UnsupportedOperationException();
	}
}
