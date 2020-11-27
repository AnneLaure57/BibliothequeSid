package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Livre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Magazine;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
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
	
	public ObservableList<Oeuvre> ListerMagazines() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
		
		ObservableList<Oeuvre> list = FXCollections.observableArrayList();
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Oeuvre> oeuvres = entityManager.createQuery("from Magazine").getResultList();
		for(Oeuvre o : oeuvres)
		{
			list.add(o);
			((Magazine) o).toString();
			System.out.println(((Magazine) o).toString());
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
		List<Oeuvre> oeuvres = entityManager.createQuery("from Livre").getResultList();
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
		List<Oeuvre> oeuvres = entityManager.createQuery("from Oeuvre where lower(titre) like '%" + recherche + "%' or lower(type) like '%" + recherche + "%'").getResultList();
	    // or auteur like '%" + recherche + "%'"
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
		// TODO Auto-generated method stub
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

	/*public void ajouterOeuvre(String titre, String description,int nombreDispo, int nombreTotal, int prix, String editeur,Date dateEdition) {
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

    public void ajouterOeuvre(String type,String titre, String description, int nombreDispo, int nombreTotal, int prix,String editeur, Date dateEdition) {
		// TODO Auto-generated method stub
	    //add oeuvre for Mag or Book
    	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        //add Oeuvre
        Oeuvre oeuvre= new Oeuvre(type, titre,description,nombreDispo, nombreTotal, prix, editeur, dateEdition);
        entityManager.persist(oeuvre);
        //Get the infos
        LOG.finer(oeuvre.toString());
        System.out.println(oeuvre.toString());

        entityTransaction.commit();
        entityManager.close();
	}*/

}
