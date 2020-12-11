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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Magazine")
public class Magazine extends Oeuvre {

	@Column(name = "numero")
	private String numero;
	
	@Column(name = "periodicite")
	private int periodicite;
	
	public Magazine(String titre, String description, int nbExemplairesDispo, int nbExemplairesTotal, Double prix, String editeur, Date dateEdition) {
		super(titre, description, prix, editeur, dateEdition);
	}
	
	public Magazine(String type, String titre, String description, Double prix,String editeur, Date dateEdition, String numero, int periodicite) {
		super(type,titre, description, prix, editeur, dateEdition);
		this.numero = numero;
		//this.dateParution = dateParution;
		this.periodicite = periodicite;
	}
	
	@Override
	public String toString() {
		return  titre;
	}
	
}
