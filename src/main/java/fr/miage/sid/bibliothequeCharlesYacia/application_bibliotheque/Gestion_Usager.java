package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.interface_utilisateur_bibliotheque.ControllerInterface;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Usager {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Usager.class.getName());
	
	public static ObservableList<Usager> ListerUsagers() 
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
	    	  
	      } catch(Exception e)
			{
				e.printStackTrace();
				entityTransaction.rollback(); 
			}
	}

	public void modifierUsager(int usagerID) {
		// TODO Auto-generated method stub
		
	}

	public void trouverUsager(int usagerID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    
		try {
		  //S2
		  // Query query = entityManager.createQuery("from Usager where id = '" + usagerID + "'");
		  Usager usager = entityManager.find(Usager.class,usagerID);
		  System.out.println(usager.toString());
		  LOG.fine(usager.toString());
		  //TODO
		  //Move functions in the other controller : formUpdU with ControllerInterface
		  // créer méthode getDate( Usager usager)
		  // Name.setText = usager.getNom()
		  // meyhods to move openformU, getData, modifierUsager
		  //TODO faire modifierUsager dans GestionBack
			
		  entityManager.close();
		    	  
		 } catch(Exception e) {
				e.printStackTrace();
				entityTransaction.rollback(); 
		 }
		
	}

}
