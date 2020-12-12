package fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "EMPRUNT" , uniqueConstraints=@UniqueConstraint(columnNames = {"date_emprunt", "titre", "nomPrenom"}) )
public class Emprunt {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_emprunt")
    private Integer id;
	
	@Column(name = "statut",nullable=false)	
	private String statut;
	
	@Column(name = "nomPrenom", nullable=false)	
	private String nomPrenom;
	
	@Column(name = "titre", nullable=false)	
	private String titre;
	
	@Column(name = "numero", nullable=false)	
	private int numero;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_emprunt",nullable=false)
	private Date dateEmprunt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_retour",nullable=false)
	private Date dateRetour;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_archivage")
	protected Date dateArchivage;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usager")
    private Usager usager = new Usager ();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oeuvre")
    private Oeuvre oeuvre = new Oeuvre ();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire = new Exemplaire ();

	public Emprunt(Usager usager, Oeuvre oeuvre, Exemplaire exemplaire, Date dateEmprunt, Date dateRetour) {
		super();
		this.usager = usager;
		this.oeuvre = oeuvre;
		this.exemplaire = exemplaire;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
		this.statut = "En cours";
		this.titre = oeuvre.getTitre();
		this.nomPrenom = usager.getNom() + " " + usager.getPrenom();
		this.numero = exemplaire.getId();
		
	}

	@Override
	public String toString() {
		return "Emprunt [id=" + id + ", statut=" + statut + ", nomPrenom=" + nomPrenom
				+ ", titre=" + titre + ", numero=" + numero + ", dateEmprunt=" + dateEmprunt + ", dateRetour="
				+ dateRetour + "]";
	}
	
}
