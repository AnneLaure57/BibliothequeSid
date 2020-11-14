package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.Date;

public class Usager {

	private String nom;
	private String prenom;
	private String adresse;
	private int codepostal;
	private String ville;
	private int telephone;
	private Date dateCreation;
	private Date dateNaissance;
	private Date dateSuppression;

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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(int codepostal) {
		this.codepostal = codepostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Date dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Usager e_identification(String nom) {
		throw new UnsupportedOperationException();
	}

	public void supprimerUsager(Usager usager) {
		throw new UnsupportedOperationException();
	}
	
}
