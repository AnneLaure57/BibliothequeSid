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

@Entity // Pour associer la classe à une table dans la base
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "RESERVATION" , uniqueConstraints=@UniqueConstraint(columnNames = {"date_reservation", "titre", "nomPrenom"})) 
public class Reservation {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_reservation")
    private Integer id;
	
	@Column(name = "statut", nullable = false)
	private String statut;
	
	@Column(name = "titre", nullable=false)	
	private String titre;
	
	@Column(name = "nomPrenom", nullable=false)	
	private String nomPrenom;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_reservation", nullable=false)
	private Date dateReservation;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_annulation")
	private Date dateAnnulation;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_archivage")
	private Date dateArchivage;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usager", nullable = false)
    private Usager usager = new Usager ();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oeuvre", nullable = false)
    private Oeuvre oeuvre = new Oeuvre ();
	
	public Reservation(Usager usager, Oeuvre oeuvre, Date dateReservation) {
		super();
		this.dateReservation = dateReservation;
		this.statut = "Réservée";
		this.usager = usager;
		this.oeuvre = oeuvre;
		this.titre = oeuvre.getTitre();
		this.nomPrenom = usager.getNom() + " " + usager.getPrenom();
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", dateReservation=" + dateReservation + ", statut=" + statut
				+ ", dateAnnulation=" + dateAnnulation + "]";
	}
	
}
