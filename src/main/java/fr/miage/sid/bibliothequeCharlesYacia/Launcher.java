package fr.miage.sid.bibliothequeCharlesYacia;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.miage.sid.bibliothequeCharlesYacia.objets_metiers_de_la_bibliotheque.Usager;
import fr.miage.sid.bibliothequeCharlesYacia.utilitaires.JPAUtil;
import javafx.application.Application;

public class Launcher {

  public static void main(String[] args) {
      Application.launch(App.class, args);
	  
	  EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();
//      entityTransaction.begin();
//      
//      Usager usager= new Usager("Yacia","Adel", "2Rue ludovic beauchet", 54000, "Nancy", "0768548385", "adel.yacia@gmail.com", new Date());
//      entityManager.persist(usager);
//
//      entityManager.getTransaction().commit();
//      entityManager.close();
//      
//      entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
//      entityTransaction = entityManager.getTransaction();
      entityTransaction.begin();
      @SuppressWarnings("unchecked")
      List<Usager> rows = entityManager.createNativeQuery( "SELECT * FROM USAGER;", Usager.class ).getResultList();
      rows.forEach(x -> System.out.println(x.toString()));
      entityManager.close();
  }
}
