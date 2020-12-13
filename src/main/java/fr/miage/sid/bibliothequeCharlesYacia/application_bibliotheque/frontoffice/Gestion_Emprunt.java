package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.frontoffice;

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
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Emprunt {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Reservation.class.getName());

	public ObservableList<Emprunt> listerEmprunts() {
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
		List<Emprunt> emprunts = entityManager.createQuery("from Emprunt where statut like '%" + recherche + "%'or  titre like '%" + recherche + "%' or nomPrenom like '%" + recherche + "%'").getResultList();

	    for(Emprunt em : emprunts)
		{
			list.add(em);
		    LOG.fine(em.toString());
			
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
	
	public ObservableList<Emprunt> listerEmpruntsUsager(int usagerID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Emprunt> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Emprunt> emprunts = entityManager.createQuery("from Emprunt where date_archivage is null and statut='En cours' and  id_usager='"+ usagerID +"'").getResultList();

	    for(Emprunt em : emprunts)
		{
			list.add(em);
		    LOG.fine(em.toString());
			
		}
	    entityManager.close();
		return list;
	}
	
	public void rendreExemplaire(int empruntID, String statut, Date dateRetour) {
      EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
      entityTransaction.begin();
		
		try {
			
    	  Query query = entityManager.createQuery("Update Emprunt "+ "set statut='" + statut + "', date_retour= '" + dateRetour +"'  where id='" + empruntID + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setStatutExemplaire(Exemplaire exemplaire, String statut) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
		    Exemplaire exemp = entityManager.find(Exemplaire.class, exemplaire.getId());
		    
		    if(exemp != null) {
		    	exemp.setStatut(statut);
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
			
		  Query query = entityManager.createQuery("Update Emprunt set date_archivage='" + dateD + "', statut='Annulé'  where id='" + empruntID + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
