package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque.backoffice;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Auteur;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Livre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Magazine;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gestion_Oeuvre {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Oeuvre.class.getName());

	public ObservableList<Oeuvre> listerOeuvres() {
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
	
	public ObservableList<Auteur> listerAuteursDispo() {
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
	
	public ObservableList<Oeuvre> listerOeuvresDispo() {
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
	
	public ObservableList<Oeuvre> trouverOeuvre(String recherche) 
	{
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    ObservableList<Oeuvre> list = FXCollections.observableArrayList();
	    entityTransaction.begin();
	    
	    @SuppressWarnings("unchecked")
		List<Oeuvre> oeuvres = entityManager.createQuery("from Oeuvre where titre like '%" + recherche + "%' or type like '%" + recherche + "%'").getResultList();
	    // or auteur like '%" + recherche + "%'"
	    for(Oeuvre o : oeuvres)
		{
			list.add(o);
		    LOG.fine(o.toString());
			
		}
	    entityManager.close();
		return list;
	}
	
	public void ajouterMagazine(String type,String titre, String description, Double prix,String editeur, Date dateEdition, String numero, int periodicite) {
		// TODO Auto-generated method stub
	     //add magazine
	     EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	     EntityTransaction entityTransaction = entityManager.getTransaction();
	     entityTransaction.begin();
	      
	     Magazine mag= new Magazine(type, titre, description, prix, editeur, dateEdition, numero, periodicite);
	     entityManager.persist(mag);
	     //Get the infos
	     LOG.finer(mag.toString());

	     entityTransaction.commit();
	     entityManager.close();
	}
	
	public void ajouterLivre(String type,String titre, String description, Double prix,String editeur, Date dateEdition, String resume) {
	     //add magazine
	     EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	     EntityTransaction entityTransaction = entityManager.getTransaction();
	     entityTransaction.begin();
	      
	     Livre livre= new Livre(type, titre, description, prix, editeur, dateEdition, resume);
	     entityManager.persist(livre);
	     //Get the infos
	     LOG.finer(livre.toString());

	     entityTransaction.commit();
	     entityManager.close();
	}
	
	public void ajouterLivreAuteur(String type,String titre, String description, Double prix,String editeur, Date dateEdition, String resume, Auteur auteur) {
	     //add magazine
	     EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	     EntityTransaction entityTransaction = entityManager.getTransaction();
	     entityTransaction.begin();
	      
	     Livre livre= new Livre(type, titre, description, prix, editeur, dateEdition, resume);
	     //Set the list of authors of the book
	     auteur.setLivres(auteur.addLivres(livre));
	     //entityManager.persist(livre);
	     entityManager.merge(livre);
	     //Get the infos
	     LOG.finer(livre.toString());
	     LOG.finer("Auteur ajouté : " + auteur.toString());

	     entityTransaction.commit();
	     entityManager.close();
	}
	
	public void supprimerOeuvre(int oeuvreID) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		  
		  try {
		    Oeuvre oeuvre = entityManager.find(Oeuvre.class,oeuvreID);
		    oeuvre.getExemplaires().clear();
		    entityManager.remove(oeuvre);
			//Remove Oeuvres + Exemplaires
			//Query query = entityManager.createQuery("Delete Oeuvre o, Exemplaire e from e inner join o where e.id_oeuvre = o.id and o.id='" + oeuvreID + "'");
			//Query query = entityManager.createQuery("Delete Exemplaire e, Oeuvre o where e.id_oeuvre = o.id and o.id='" + oeuvreID + "'");
			//query.executeUpdate();
	
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
			
		  Query query = entityManager.createQuery("Update Oeuvre set date_archivage='" + dateD + "'  where id='" + oeuvreID  + "'");
		  query.executeUpdate();
			
		  entityTransaction.commit();
	      entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//TODO archive Exemplaires
	}
	
	public void setNbTotalDisponibles(Oeuvre oeuvre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
	    	Oeuvre o = entityManager.find(Oeuvre.class, oeuvre.getId());
		    
		    if(o != null) {
		    	o.setNbExemplairesDispo(o.getNbExemplairesDispo()+1);
		    	o.setNbExemplairesTotal(o.getNbExemplairesTotal()+1);
		    }

		    //Get the infos
		    LOG.finer(o.toString());
		    entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
		
	}

	public void setNbTotalDispoSup(Oeuvre oeuvre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
	    	Oeuvre o = entityManager.find(Oeuvre.class, oeuvre.getId());
		    
		    if(o != null) {
		    	o.setNbExemplairesDispo(o.getNbExemplairesDispo()-1);
		    	o.setNbExemplairesTotal(o.getNbExemplairesTotal()-1);
		    }

		    //Get the infos
		    LOG.finer(o.toString());
		    entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
		
	}
	
	public void setNbTotalSup(Oeuvre oeuvre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
	    	Oeuvre o = entityManager.find(Oeuvre.class, oeuvre.getId());
		    
		    if(o != null) {
		    	o.setNbExemplairesTotal(o.getNbExemplairesTotal()-1);
		    }

		    //Get the infos
		    LOG.finer(o.toString());
		    entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
		
	}
	
	public void setNbDisponibles(Oeuvre oeuvre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
	    	Oeuvre o = entityManager.find(Oeuvre.class, oeuvre.getId());
		    
		    if(o != null) {
		    	o.setNbExemplairesDispo(o.getNbExemplairesDispo()+1);
		    }

		    //Get the infos
		    LOG.finer(o.toString());
		    entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
		
	}

	public void setNbIndisponibles(Oeuvre oeuvre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
	    	Oeuvre o = entityManager.find(Oeuvre.class, oeuvre.getId());
		    
		    if(o != null) {
		    	o.setNbExemplairesDispo(o.getNbExemplairesDispo()-1);
		    }

		    //Get the infos
		    LOG.finer(o.toString());
		    entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
		
	}
	
	public void setNbResAjout(Oeuvre oeuvre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
	    	Oeuvre o = entityManager.find(Oeuvre.class, oeuvre.getId());
		    
		    if(o != null) {
		    	o.setNbResa(o.getNbResa()+1);
		    }

		    //Get the infos
		    LOG.finer(o.toString());
		    entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
		
	}
	
	public void setNbResaSup(Oeuvre oeuvre) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
	    entityTransaction.begin();
	    try {
	    	Oeuvre o = entityManager.find(Oeuvre.class, oeuvre.getId());
		    
		    if(o != null) {
		    	o.setNbResa(o.getNbResa()-1);
		    }

		    //Get the infos
		    LOG.finer(o.toString());
		    entityTransaction.commit();
	      
	    }
	    catch (RuntimeException e) {
	        entityManager.getTransaction().rollback();
	        throw e;
	    }
	    
	      entityManager.close();
		
	}

}
