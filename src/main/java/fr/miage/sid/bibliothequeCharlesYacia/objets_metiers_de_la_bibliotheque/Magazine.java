package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Magazine")
public class Magazine extends Oeuvre {

	//TODO : find why num is consider as id, even if we add another id with @
	@Column(name = "numero")
	private String numero;
	
	/*@Temporal(TemporalType.DATE)
	@Column(name = "date_parution")
	private Date dateParution;*/
	
	@Column(name = "periodicite")
	private int periodicite;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/*public Date getDateParution() {
		return dateParution;
	}
	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}*/
	public int getPeriodicite() {
		return periodicite;
	}
	public void setPeriodicite(int periodicite) {
		this.periodicite = periodicite;
	}
	
	public Magazine(String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, int prix, String editeur, Date dateEdition) {
		super(titre, description, nbExemplairesDispo, nbExemplairesTotal, prix, editeur, dateEdition);
	}
	
	/*public Magazine(String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, int prix,String editeur, Date dateEdition, String numero, int periodicite) {
		super(titre, description, nbExemplairesDispo, nbExemplairesTotal, prix, editeur, dateEdition);
		this.numero = numero;
		//this.dateParution = dateParution;
		this.periodicite = periodicite;
	}*/
	
	public Magazine(String type, String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, int prix,String editeur, Date dateEdition, String numero, int periodicite) {
		super(type,titre, description, nbExemplairesDispo, nbExemplairesTotal, prix, editeur, dateEdition);
		this.numero = numero;
		//this.dateParution = dateParution;
		this.periodicite = periodicite;
	}
	
	/*public String toString() {
		return "Magazine [ id=" + id + ", type=" + type
				+ ", titre=" + titre + ", description=" + description + ", nbExemplairesDispo=" + nbExemplairesDispo
				+ ", nbExemplairesTotal=" + nbExemplairesTotal + ", prix=" + prix + ", nbResa=" + nbResa + ", editeur="
				+ editeur + ", dateEdition=" + dateEdition + ", dateSuppression=" + dateArchivage + "numero=" + numero + ", periodicite=" + periodicite + "]";
	}*/
	
	@Override
	public String toString() {
		return  titre;
	}
	
}
