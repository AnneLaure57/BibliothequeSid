package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

public class Magazine extends Oeuvre {

	private int numero;
	private int dateParution;
	private int periodicite;

	public void getNumero() {
		throw new UnsupportedOperationException();
	}

	public void setNumero(int Numero) {
		this.numero = Numero;
	}

	public void getPeriodicite() {
		throw new UnsupportedOperationException();
	}

	public void setPeriodicite(int periodicite) {
		this.periodicite = periodicite;
	}
}
