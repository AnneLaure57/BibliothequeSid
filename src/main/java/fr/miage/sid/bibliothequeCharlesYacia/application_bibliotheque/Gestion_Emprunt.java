package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Emprunt;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Reservation;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Emprunt {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Reservation.class.getName());

	public ObservableList<Emprunt> ListerEmprunts() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Emprunt> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Emprunt> emprunts = entityManager.createQuery("from Emprunt").getResultList();
		for(Emprunt em : emprunts)
		{
			list.add(em);
		}
		entityManager.close();
		return list;
	}
	
	public ObservableList<Emprunt> trouverEmprunt(String recherche) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Emprunt> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Emprunt> emprunts = entityManager.createQuery("from Emprunt where lower(statut) like '%" + recherche + "%'or  lower(titre) like '%" + recherche + "%' or lower(nomPrenom) like '%" + recherche + "%'").getResultList();

	    for(Emprunt em : emprunts)
		{
			list.add(em);
		    LOG.fine(em.toString());
			
		}
	    entityManager.close();
		return list;
	}
	
	public ObservableList<Exemplaire> ListerExemplairesUnDeleted(int oeuvreID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Exemplaire> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Exemplaire> exemplaires = entityManager.createQuery("from Exemplaire where date_archivage is null and statut='Disponible' and  id_oeuvre='"+ oeuvreID +"'").getResultList();

	    for(Exemplaire ex : exemplaires)
		{
			list.add(ex);
		    LOG.fine(ex.toString());
			
		}
	    entityManager.close();
		return list;
	}

	public ObservableList<Oeuvre>  ListerOeuvresUnDeleted() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Oeuvre> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Oeuvre> oeuvres = entityManager.createQuery("from Oeuvre where date_archivage is null ").getResultList();
		for(Oeuvre o : oeuvres)
		{
			list.add(o);
		}
		entityManager.close();
		return list;
	}

	public ObservableList<Usager> ListerUsagersUnDeleted() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Usager> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Usager> usagers = entityManager.createQuery("from Usager where date_archivage is null ").getResultList();
		for(Usager us : usagers)
		{
			list.add(us);
		}
		entityManager.close();
		return list;
	}
	
	public void emprunterExemplaire(Oeuvre oeuvre, Usager usager, Exemplaire exemplaire, Date dateEmprunt, Date dateRetour) {
		  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		  EntityTransaction entityTransaction = entityManager.getTransaction();
		  entityTransaction.begin();
		  
		  Emprunt emprunt= new Emprunt(usager, oeuvre, exemplaire, dateEmprunt, dateRetour);
		  entityManager.persist(emprunt);
		  //Get the infos
		  LOG.finer(emprunt.toString());
		
		  entityTransaction.commit();
		  entityManager.close();
		
	}
	
	public void setStatutExemplaire(Exemplaire exemplaire) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
		    Exemplaire exemp = entityManager.find(Exemplaire.class, exemplaire.getId());
		    
		    if(exemp != null) {
		    	exemp.setStatut("Emprunté");
		    }
		      LOG.finer(exemp.toString());
		
		      entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
		
	}
	
	public void supprimerEmprunt(int empruntID) {
	  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
      entityTransaction.begin();
		  
	  try {
	    Emprunt emprunt = entityManager.find(Emprunt.class,empruntID);
	    entityManager.remove(emprunt);
	
	    entityTransaction.commit();
	    entityManager.close();
		  
	  } catch(Exception e) {
		  e.printStackTrace();
		  entityTransaction.rollback(); 
	  }
		
	}

	public void archiverEmprunt(int empruntID) {
		Calendar calendar = Calendar.getInstance();
		 java.sql.Date dateD = new java.sql.Date(calendar.getTime().getTime());
		    
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
		try {
			
		  Query query = entityManager.createQuery("Update Emprunt set date_archivage='" + dateD + "', statut='Annulée'  where id='" + empruntID + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
