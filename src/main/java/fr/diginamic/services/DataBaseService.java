package fr.diginamic.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataBaseService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConfigDev1");;

    public static EntityManager connexionDataBase (){
        return emf.createEntityManager();
    }

    public void insertionDataBase(String monPath) {
        EntityManager em = connexionDataBase();
        EntityTransaction transaction = em.getTransaction();

        CsvService csvService = new CsvService();
        //String[] mesValeurs = csvService.traitementDesFilms(monPath);
        //System.out.println(Arrays.toString(mesValeurs));
        transaction.begin();



        transaction.commit();
    }
}