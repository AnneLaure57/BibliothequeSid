package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.frontoffice;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Emprunt;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Reservation;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Reservation {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Reservation.class.getName());

	public static ObservableList<Reservation> listerReservations() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Reservation> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Reservation> reservations = entityManager.createQuery("from Reservation").getResultList();
		for(Reservation res : reservations)
		{
			list.add(res);
		}
		entityManager.close();
		return list;
	}
	
	public ObservableList<Reservation> listerReservationsUsager(int usagerID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Reservation> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Reservation> reservations = entityManager.createQuery("from Reservation where date_archivage is null and statut='Réservée' and  id_usager='"+ usagerID +"'").getResultList();

	    for(Reservation res : reservations)
		{
			list.add(res);
		    LOG.fine(res.toString());
			
		}
	    entityManager.close();
		return list;
	}

	public static ObservableList<Reservation> trouverReservation(String recherche) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Reservation> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Reservation> reservations = entityManager.createQuery("from Reservation where statut like '%" + recherche + "%'or  titre like '%" + recherche + "%' or nomPrenom like '%" + recherche + "%'").getResultList();

	    for(Reservation res : reservations)
		{
			list.add(res);
		    LOG.fine(res.toString());
			
		}
	    entityManager.close();
		return list;
	}

	public void reserverOeuvre(Oeuvre oeuvre, Usager usager, Date dateReservation) {
	  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	  EntityTransaction entityTransaction = entityManager.getTransaction();
	  entityTransaction.begin();
	  
	  Reservation reservation= new Reservation(usager, oeuvre, dateReservation);
	  entityManager.persist(reservation);
	  //Get the infos
	  LOG.finer(reservation.toString());
	
	  entityTransaction.commit();
	  entityManager.close();
	}

	public void supprimerReservation(int reservationID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		  
		  try {
		    Reservation reservation = entityManager.find(Reservation.class,reservationID);
		    entityManager.remove(reservation);
		
		    entityTransaction.commit();
		    entityManager.close();
			  
		  } catch(Exception e) {
			  e.printStackTrace();
			  entityTransaction.rollback(); 
		  }
		
	}

	public void archiverReservation(int reservationID) {
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Date dateD = new java.sql.Date(calendar.getTime().getTime());
		    
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
		try {
			
		  Query query = entityManager.createQuery("Update Reservation set date_archivage='" + dateD + "', statut='Annulée'  where id='" + reservationID + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback(); 
		}
		
	}

	public void annulerReservation(int reservationID) {
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Date dateD = new java.sql.Date(calendar.getTime().getTime());
		    
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
		try {
			
		  Query query = entityManager.createQuery("Update Reservation set date_annulation='" + dateD + "', statut='Annulée'  where id='" + reservationID + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback(); 
		}
		
	}

	public  static ObservableList<Reservation> verifierReservation(Oeuvre oeuvre, Usager usager) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Reservation> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Reservation> reservations = entityManager.createQuery("from Reservation where id_usager='" +  usager.getId() + "' and id_oeuvre='" + oeuvre.getId() + "' and statut='Réservée'").getResultList();

	    for(Reservation res : reservations)
		{
			list.add(res);
		    LOG.fine(res.toString());
		    //System.out.println(res.toString());
			
		}
	    entityTransaction.commit();
	    entityManager.close();
		return list;
	}

	public static ObservableList<Reservation> verifierReservationEmprunt(Oeuvre oeuvre) {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Reservation> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Reservation> reservations = entityManager.createQuery("from Reservation where id_oeuvre='" + oeuvre.getId() + "' and statut='Réservée' order by date_reservation").getResultList();

	    for(Reservation res : reservations)
		{
			list.add(res);
		    LOG.fine(res.toString());
		    //System.out.println(res.toString());
			
		}
	    entityManager.close();
		return list;
	}

}
