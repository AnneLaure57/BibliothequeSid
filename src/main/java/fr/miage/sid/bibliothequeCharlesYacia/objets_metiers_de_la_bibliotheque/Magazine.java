package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("Magazine")
public class Magazine extends Oeuvre {
	
	
	private int numero;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_parution")
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
	
	
	public Magazine(String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, int prix,
			String editeur, Date dateEdition) {
		super(titre, description, nbExemplairesDispo, nbExemplairesTotal, prix, editeur, dateEdition);
	}
	
	public Magazine(String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, int prix,
			String editeur, Date dateEdition, int numero, Date dateParution, int periodicite) {
		super(titre, description, nbExemplairesDispo, nbExemplairesTotal, prix, editeur, dateEdition);
		this.numero = numero;
		this.dateParution = dateParution;
		this.periodicite = periodicite;
	}
	@Override
	public String toString() {
		return "Magazine [numero=" + numero + ", dateParution=" + dateParution + ", periodicite=" + periodicite + "]";
	}

	
}
