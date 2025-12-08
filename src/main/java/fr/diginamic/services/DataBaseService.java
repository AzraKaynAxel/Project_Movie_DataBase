package fr.diginamic.services;

import fr.diginamic.entites.Acteur;
import fr.diginamic.entites.Film;
import fr.diginamic.entites.LieuNaissance;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.Personne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConfigDev1");;
    private static final String PARAM_REQUETE = "SELECT COUNT(e) FROM LieuNaissance e WHERE e.localisation = :value";

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
        } else if (monPath.contains("pays.csv")) {
            List<Pays> mesPays = csvService.traitementDesPays(monPath);
            for (Pays pays : mesPays){
                em.persist(pays);
            }
            /*List<Personne> realisateurs = csvService.traitementDesRealisateurs(monPath);
            for (Personne personne : realisateurs) {
                em.persist(personne);
            }*/
        } else if (monPath.contains("acteurs.csv")) {
            List<LieuNaissance> mesLieuNaissances = csvService.traitementDesLieuNaissance(monPath);
            List<Acteur> mesActeurs = csvService.traitementDesActeurs(monPath);

            for (LieuNaissance l : mesLieuNaissances){
                TypedQuery<Long> query = em.createQuery(PARAM_REQUETE, Long.class);
                query.setParameter("value", l.getLocalisation());
                Long count = query.getSingleResult();
                if (count > 0) {
                    System.out.println(l + "déjà existant");
                } else {
                    em.persist(l);
                }
            }

        } else if (monPath.contains("realisateurs.csv")) {
            List<LieuNaissance> mesLieuNaissances = csvService.traitementDesLieuNaissance(monPath);
            List<Personne> mesRealisateurs = new ArrayList<>();
            for (LieuNaissance l : mesLieuNaissances){
                TypedQuery<Long> query = em.createQuery(PARAM_REQUETE, Long.class);
                query.setParameter("value", l.getLocalisation());
                Long count = query.getSingleResult();
                if (count > 0) {
                    System.out.println(l + "déjà existant");
                } else {
                    em.persist(l);
                }
            }
        } else {
            System.out.println("coucou");
        }

        transaction.commit();
        //String[] mesValeurs = csvService.traitementDesFilms(monPath);
        //System.out.println(Arrays.toString(mesValeurs));
    }
}