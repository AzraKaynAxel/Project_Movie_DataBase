package fr.diginamic.services;

import fr.diginamic.entites.Film;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.Personne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DataBaseService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConfigDev1");;

    public static EntityManager connexionDataBase (){
        return emf.createEntityManager();
    }

    public void insertionDataBase(String monPath) {
        EntityManager em = connexionDataBase();
        EntityTransaction transaction = em.getTransaction();

        CsvService csvService = new CsvService();

        transaction.begin();

        if (monPath.contains("films.csv")) {
            List<Film> films = csvService.traitementDesFilms(monPath);
            System.out.println(films);
            for (Film film : films) {
                em.persist(film);
            }
        } else {
            List<Pays> mesPays = csvService.traitementDesPays(monPath);
            for (Pays pays : mesPays){
                em.persist(pays);
            }
            /*List<Personne> realisateurs = csvService.traitementDesRealisateurs(monPath);
            for (Personne personne : realisateurs) {
                em.persist(personne);
            }*/
        }

        transaction.commit();
        //String[] mesValeurs = csvService.traitementDesFilms(monPath);
        //System.out.println(Arrays.toString(mesValeurs));
    }
}