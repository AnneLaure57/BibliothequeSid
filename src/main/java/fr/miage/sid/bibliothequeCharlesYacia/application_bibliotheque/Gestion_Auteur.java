package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Auteur;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Livre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Auteur {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Auteur.class.getName());

	public ObservableList<Auteur> ListerAuteurs() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Auteur> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Auteur> auteurs = entityManager.createQuery("from Auteur").getResultList();
		for(Auteur au : auteurs)
		{
			list.add(au);
			//System.out.println(au.toString());
		}
		entityManager.close();
		return list;
	}

	public ObservableList<Auteur> ListerAuteursDispo() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Auteur> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Auteur> auteurs = entityManager.createQuery("from Auteur where date_archivage is null ").getResultList();
		for(Auteur au : auteurs)
		{
			list.add(au);
		}
		entityManager.close();
		return list;
	}
	
	public ObservableList<Oeuvre> ListerLivres() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Oeuvre> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Oeuvre> oeuvres = entityManager.createQuery("from Oeuvre where type='Livre'").getResultList();
		for(Oeuvre o : oeuvres)
		{
			list.add(o);
		}
		entityManager.close();
		return list;
	}

	public ObservableList<Auteur> trouverAuteur(String recherche) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Auteur> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Auteur> auteurs = entityManager.createQuery("from Auteur where nom like '%" + recherche + "%' or prenom like '%" + recherche + "%'").getResultList();

	    for(Auteur au : auteurs)
		{
			list.add(au);
			
		}
	    entityManager.close();
		return list;
	}

	public void ajouterAuteur(String nom, String prenom) {
	  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
      entityTransaction.begin();
      
      Auteur auteur = new Auteur(nom,prenom);
      entityManager.persist(auteur);

      entityTransaction.commit();
      entityManager.close();
		
	}
	
	public void ajouterAuteurLivre(String nom, String prenom, Livre livre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	      EntityTransaction entityTransaction = entityManager.getTransaction();
	      entityTransaction.begin();
	      
	      Auteur auteur = new Auteur(nom,prenom);
	      //auteur.addLivres(livre);
	      //Set the list of books of the auteur
	      auteur.setLivres(auteur.addLivres(livre));
	      entityManager.persist(auteur);
	      entityManager.merge(auteur);
	      //Get the infos
	     // System.out.println(auteur.toString());
	      //System.out.println(auteur.getLivres());
	      LOG.finer("Auteur ajouté : " + auteur.toString());

	      entityTransaction.commit();
	      entityManager.close();
		
	}

	public void modifierAuteur(int id, String nom, String prenom) {
	  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
      entityTransaction.begin();
		
		try {
			
    	  Query query = entityManager.createQuery("Update Auteur set nom='" + nom + "', prenom= '" + prenom +"'  where id='" + id + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void supprimerAuteur(int usagerID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		  
		  try {
		    Auteur auteur = entityManager.find(Auteur.class,usagerID);
		    entityManager.remove(auteur);
		    System.out.println(auteur);
		    entityTransaction.commit();
	        entityManager.close();
			  
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
		
	}

	public void archiverAuteur(int auteurID) {
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Date dateD = new java.sql.Date(calendar.getTime().getTime());
		    
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();
		try {
			
		  Query query = entityManager.createQuery("Update Auteur set date_archivage='" + dateD + "'  where id='" + auteurID + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Auteur trouverAuteur(int usagerID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    Auteur auteur = null;
		try {
		  auteur = entityManager.find(Auteur.class,usagerID);
		  LOG.fine(auteur.toString());
			
		  entityManager.close();
		    	  
		 } catch(Exception e) {
				e.printStackTrace();
				entityTransaction.rollback(); 
		 }
		return auteur;
	}

	public void lierAuteurLivre(Auteur auteur, Livre livre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
		auteur.setLivres(auteur.addLivres(livre));
	    entityManager.merge(auteur);
	    //Get the infos
	    // System.out.println(auteur.toString());
	    //System.out.println(auteur.getLivres());
	    LOG.finer("Auteur ajouté : " + auteur.toString());

	    entityTransaction.commit();
	    entityManager.close();
		
	}

}
