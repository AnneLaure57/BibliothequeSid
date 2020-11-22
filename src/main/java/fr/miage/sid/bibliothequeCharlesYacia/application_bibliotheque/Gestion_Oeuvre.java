package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Oeuvre {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Oeuvre.class.getName());

	public ObservableList<Oeuvre> ListerOeuvres() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Oeuvre> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Oeuvre> oeuvres = entityManager.createQuery("from Oeuvre").getResultList();
		for(Oeuvre o : oeuvres)
		{
			list.add(o);
		}
		entityManager.close();
		return list;
	}
	
	public ObservableList<Oeuvre> trouverOeuvre(String text) 
	{
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Oeuvre> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Oeuvre> oeuvres = entityManager.createQuery("from Oeuvre where titre like '%" + text + "%'").getResultList();

	    for(Oeuvre o : oeuvres)
		{
			list.add(o);
		    LOG.fine(o.toString());
			
		}
	    entityManager.close();
		return list;
	}

	public void supprimerOeuvre(int oeuvreID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		  
		  try {
		    Oeuvre oeuvre = entityManager.find(Oeuvre.class,oeuvreID);
		    entityManager.remove(oeuvre);
	
	        entityTransaction.commit();
	        entityManager.close();
			  
		  } catch(Exception e) {
			  e.printStackTrace();
			  entityTransaction.rollback(); 
		  }
		
	}

	public void archiverOeuvre(int oeuvreID) {
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Date dateD = new java.sql.Date(calendar.getTime().getTime());
		    
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
		try {
			
		  Query query = entityManager.createQuery("Update Oeuvre set date_suppression='" + dateD + "'  where id='" + oeuvreID  + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void ajouterOeuvre(String titre, String description,int nombreDispo, int nombreTotal, int prix, String editeur,Date dateEdition) {
	  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
      entityTransaction.begin();
      
      Oeuvre oeuvre= new Oeuvre(titre,description,nombreDispo, nombreTotal, prix, editeur, dateEdition);
      entityManager.persist(oeuvre);
      //Get the infos
      LOG.finer(oeuvre.toString());

      entityTransaction.commit();
      entityManager.close();
		
	}

}
