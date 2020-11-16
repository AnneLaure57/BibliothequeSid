package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.sql.Date;
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

public class Gestion_Back {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Back.class.getName());
	
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
	
	      entityManager.getTransaction().commit();
	      entityManager.close();
	      
	      entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	      entityTransaction = entityManager.getTransaction();
	}

	public void ajouterOeuvre(String titre) {
		throw new UnsupportedOperationException();
	}

	public void supprimerExemplaire(Oeuvre oeuvre) {
		throw new UnsupportedOperationException();
	}

	public void supprimerOeuvre(String titre) {
		throw new UnsupportedOperationException();
	}

	public void supprimerUsager(String nom) {
		throw new UnsupportedOperationException();
	}

	public void modifierUsager(Usager usager) {
		throw new UnsupportedOperationException();
	}

	public void modifierExemplaire(Exemplaire exemplaire) {
		throw new UnsupportedOperationException();
	}
}
