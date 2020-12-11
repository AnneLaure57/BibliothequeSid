package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Exemplaire {

	private static final Logger LOG = Logger.getLogger(Gestion_Exemplaire.class.getName());
	
	public ObservableList<Exemplaire> listerExemplaires() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Exemplaire> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Exemplaire> exemplaires = entityManager.createQuery("from Exemplaire").getResultList();
		for(Exemplaire ex : exemplaires)
		{
			list.add(ex);
		}
		entityTransaction.commit();
		entityManager.close();
		return list;
	}
	
	public ObservableList<Exemplaire> listerExemplairesDispo() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Exemplaire> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Exemplaire> exemplaires = entityManager.createQuery("from Exemplaire where date_archivage is null and statut='Disponible'").getResultList();

	    for(Exemplaire ex : exemplaires)
		{
			list.add(ex);
		    LOG.fine(ex.toString());
			
		}
	    entityTransaction.commit();
	    entityManager.close();
		return list;
	}


	public ObservableList<String> listerOeuvres() {
		// TODO Auto-generated method stub
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<String> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<String> oeuvres = entityManager.createNativeQuery("select titre from Oeuvre where date_archivage is null").getResultList();
		for(String oe : oeuvres)
		{
			list.add(oe);
		}
		entityTransaction.commit();
		entityManager.close();
		return list;
	}

	

	public void ajouterExemplaire(String etat, Oeuvre oeuvre ) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	      EntityTransaction entityTransaction = entityManager.getTransaction();
	      entityTransaction.begin();
	      
	      Exemplaire exemplaire = new Exemplaire(oeuvre, etat);
	      entityManager.persist(exemplaire);
	      //Get the infos
	      LOG.finer(exemplaire.toString());
	
	      entityTransaction.commit();
	      entityManager.close();
	}

	public Oeuvre getOeuvreParNom(Object titre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
	      
		Oeuvre oeuvre = (Oeuvre) entityManager.createNativeQuery( "SELECT * FROM Oeuvre WHERE titre ='"+ titre.toString() +"';", Oeuvre.class ).getSingleResult();
		
		entityTransaction.commit();
	    entityManager.close();
	    
		return oeuvre;
	}

	public Exemplaire trouverExemplaireParID(int exemplaireID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    Exemplaire exemplaire = null;
		try {
		  
		  exemplaire = entityManager.find(Exemplaire.class,exemplaireID);
		  LOG.fine(exemplaire.toString());

		  entityTransaction.commit();
		  entityManager.close();
		    	  
		 } catch(Exception e) {
				e.printStackTrace();
				entityTransaction.rollback(); 
		 }
		return exemplaire;
	}
	
	public ObservableList<Exemplaire> trouverOeuvre(String recherche) 
	{
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Exemplaire> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Exemplaire> exemplaires = entityManager.createQuery("from Exemplaire where titre like '%" + recherche + "%' ").getResultList();
	    // or auteur like '%" + recherche + "%'"
	    for(Exemplaire e : exemplaires)
		{
			list.add(e);
		    LOG.fine(e.toString());
			
		}
	    entityTransaction.commit();
	    entityManager.close();
		return list;
	}

	public void modifierExemplaire(Exemplaire exemp, String etat) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
		    Exemplaire exemplaire = entityManager.find(Exemplaire.class, exemp.getId());
		    
		    if(exemplaire != null) {
		    	exemplaire.setEtat(etat);
		    }

		    //Get the infos
		    LOG.finer(exemplaire.toString());
		    entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
	}

	public void supprimerExemplaire(int exemplaireID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		  
		  try {
			
		    Exemplaire exemplaire = entityManager.find(Exemplaire.class,exemplaireID);
		    entityManager.remove(exemplaire);
	
	        entityTransaction.commit();
	        entityManager.close();
			  
		  } catch(Exception e) {
			  e.printStackTrace();
			  entityTransaction.rollback(); 
		  }
	}

	public void archiverExemplaire(int exemplaireID) {
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Date dateD = new java.sql.Date(calendar.getTime().getTime());
		    
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
		try {
			
		  Query query = entityManager.createQuery("Update Exemplaire set date_archivage='" + dateD + "', statut='Indisponible'  where id='" + exemplaireID + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback(); 
		}
	}

}
