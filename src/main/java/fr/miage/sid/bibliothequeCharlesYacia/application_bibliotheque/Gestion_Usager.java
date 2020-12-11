package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Usager {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Usager.class.getName());
	
	public static ObservableList<Usager> listerUsagers() 
	{
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Usager> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Usager> usagers = entityManager.createQuery("from Usager").getResultList();
		for(Usager us : usagers)
		{
			list.add(us);
		}
		entityTransaction.commit();
		entityManager.close();
		return list;
	}
	
	public static ObservableList<Usager> listerUsagersDispo() 
	{
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
		entityTransaction.commit();
		entityManager.close();
		return list;
	}
	
	public static ObservableList<Usager> trouverUsager(String text) 
	{
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Usager> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Usager> usagers = entityManager.createQuery("from Usager where nom like '%" + text + "%' or prenom like '%" + text + "%'").getResultList();

	    for(Usager us : usagers)
		{
			list.add(us);
		    LOG.fine(us.toString());
			
		}
	    entityTransaction.commit();
	    entityManager.close();
		return list;
	}

	public void ajouterUsager(String nom, String prenom, String adresse, int codePostal, String ville, String telephone, String mail, Date dateNaissance) {
		  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	      EntityTransaction entityTransaction = entityManager.getTransaction();
	      entityTransaction.begin();
	      
	      Usager usager= new Usager(nom,prenom, adresse, codePostal, ville, telephone, mail, dateNaissance);
	      entityManager.persist(usager);
	      //Get the infos
	      LOG.finer(usager.toString());
	
	      entityTransaction.commit();
	      entityManager.close();
	}
	
	public void modifierUsager(int id, String nom, String prenom, String adresse, int codePostal, String ville, String telephone, String email, Date dateNaissance) {
		
	  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
      entityTransaction.begin();
		
		try {
			
    	  Query query = entityManager.createQuery("Update Usager "+ "set nom='" + nom + "', prenom= '" + prenom +"', adresse='" + adresse +"', code_postal='" + codePostal + 
    			  "', ville='" + ville +"', telephone='"+ telephone +"', mail='" + email + "', date_naissance='" + dateNaissance +"'  where id='" + id + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void supprimerUsager(int usagerID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		  
		  try {
			//Get the infos
		    //S1
			//	    	  Query query = entityManager.createQuery("delete from Usager where id = '" + usagerID + "'");
		    //			  query.executeUpdate();
		    //S2
		    Usager usager = entityManager.find(Usager.class,usagerID);
		    entityManager.remove(usager);
	
	        entityTransaction.commit();
	        entityManager.close();
			  
		  } catch(Exception e) {
			  e.printStackTrace();
			  entityTransaction.rollback(); 
		  }
	}
	
	public void archiverUsager(int id) {
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Date dateD = new java.sql.Date(calendar.getTime().getTime());
		    
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
		try {
			
		  Query query = entityManager.createQuery("Update Usager set date_archivage='" + dateD + "'  where id='" + id + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Usager trouverUsagerParID(int usagerID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    Usager usager = null;
		try {
		  //S2
		  //Query query = entityManager.createQuery("from Usager where id = '" + usagerID + "' and date_supression=''");
		  //LOG.fine(query.toString());
	      //query.executeUpdate();
		  usager = entityManager.find(Usager.class,usagerID);
		  LOG.fine(usager.toString());
		  entityTransaction.commit();
		  entityManager.close();
		    	  
		 } catch(Exception e) {
				e.printStackTrace();
				entityTransaction.rollback(); 
		 }
		return usager;
		
	}
	
}
