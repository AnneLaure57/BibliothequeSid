package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.Date;

public class Magazine extends Oeuvre {

	private int numero;
	private Date dateParution;
	private int periodicite;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDateParution() {
		return dateParution;
	}
	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}
	public int getPeriodicite() {
		return periodicite;
	}
	public void setPeriodicite(int periodicite) {
		this.periodicite = periodicite;
	}

	
}
