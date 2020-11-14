package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

public class Auteur {

	private String nom;
	private String prenom;
	
	public Auteur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
