package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.*;

public class Reservation {

	private Date dateReservation;
	private String statut;
	private Date dateAnnulation;

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getDateAnnulation() {
		return dateAnnulation;
	}

	public void setDateAnnulation(Date dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
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
