package fr.miage.sid.bibliothequeCharlesYacia.application_bibliotheque;

import java.sql.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Exemplaire;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Oeuvre;
import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;

public class Gestion_Back {
	
	private static final Logger LOG = Logger.getLogger(Gestion_Back.class.getName());

	public void ajouterUsager(String LastName, String firstName, String adress, int cp, String city, String tel, String mail, Date dateBirth) {
		  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	      EntityTransaction entityTransaction = entityManager.getTransaction();
	      entityTransaction.begin();
	      
	      Usager usager= new Usager(LastName,firstName, adress, cp, city, tel, mail, dateBirth);
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
