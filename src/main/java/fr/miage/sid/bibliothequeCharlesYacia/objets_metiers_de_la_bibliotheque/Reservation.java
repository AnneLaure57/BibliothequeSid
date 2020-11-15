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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Pour associer la classe à une table dans la base
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "RESERVATION" ) // Apparemment ça sert à rien sauf si on veut que la table ait un nom différent de la classe
public class Reservation {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_reservation", nullable=false)
	private Date dateReservation;
	
	@Column(name = "statut", nullable = false)
	private String statut;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_annulation")
	private Date dateAnnulation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usager", nullable = false)
    private Usager usager = new Usager ();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oeuvre", nullable = false)
    private Oeuvre oeuvre = new Oeuvre ();
	
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

	public Reservation(Usager usager, Oeuvre oeuvre) {
		super();
		this.dateReservation = new Date();
		this.statut = "Actif";
		this.usager = usager;
		this.oeuvre = oeuvre;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", dateReservation=" + dateReservation + ", statut=" + statut
				+ ", dateAnnulation=" + dateAnnulation + "]";
	}
	
	
}
