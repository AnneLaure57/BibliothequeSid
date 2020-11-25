package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Exemplaire {

	private static final Logger LOG = Logger.getLogger(Gestion_Exemplaire.class.getName());
	
	public ObservableList<Exemplaire> ListerExemplaire() {
		// TODO Auto-generated method stub
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
		entityManager.close();
		return list;
	}

	public ObservableList<String> ListerOeuvre() {
		// TODO Auto-generated method stub
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<String> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<String> oeuvres = entityManager.createNativeQuery("select titre from Oeuvre").getResultList();
		for(String oe : oeuvres)
		{
			list.add(oe);
		}
		entityManager.close();
		return list;
	}

	

	public void ajouterExemplaire(String etat, Oeuvre oeuvre ) {
		// TODO Auto-generated method stub
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

	public Oeuvre getOeuvreByName(Object titre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
	      
		Oeuvre oeuvre = (Oeuvre) entityManager.createNativeQuery( "SELECT * FROM Oeuvre WHERE titre ='"+ titre.toString() +"';", Oeuvre.class ).getSingleResult();
		
		entityTransaction.commit();
	    entityManager.close();
	    
		return oeuvre;
	}

	public Exemplaire trouverExemplaire(int exemplaireID) {
		// TODO Auto-generated method stub
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    Exemplaire exemplaire = null;
		try {
		  
		  exemplaire = entityManager.find(Exemplaire.class,exemplaireID);
		  LOG.fine(exemplaire.toString());
			
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
		List<Exemplaire> exemplaires = entityManager.createQuery("from Exemplaire where lower(titre) like '%" + recherche + "%' ").getResultList();
	    // or auteur like '%" + recherche + "%'"
	    for(Exemplaire e : exemplaires)
		{
			list.add(e);
		    LOG.fine(e.toString());
			
		}
	    entityManager.close();
		return list;
	}

	public void modiferExemplaire(Exemplaire exemp, String etat) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
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

}
