package fr.diginamic.services;

import fr.diginamic.entites.Acteur;
import fr.diginamic.entites.Film;
import fr.diginamic.entites.Genre;
import fr.diginamic.entites.LieuNaissance;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.Personne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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

            /**
             * Première partie pour insérer les genres
             */

            List<Genre> mesGenres = csvService.traitementDesGenres(monPath);

            for (Genre genre : mesGenres) {
                em.persist(genre);
            }

            /**
             * Deuxème partie pour insérer les Acteurs
             */
            List<Film> films = csvService.traitementDesFilms(monPath, em);

            for (Film film : films) {
                em.persist(film);
            }
        } else if (monPath.contains("pays.csv")) {
            List<Pays> mesPays = csvService.traitementDesPays(monPath);
            for (Pays pays : mesPays){
                em.persist(pays);
            }
        } else if (monPath.contains("acteurs.csv")) {

            /**
             * Première partie pour insérer les LieuxNaissance
             */
            List<LieuNaissance> mesLieuNaissances = csvService.traitementDesLieuNaissance(monPath);

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

            /**
             * Deuxème partie pour insérer les Acteurs
             */
            List<Acteur> mesActeurs = csvService.traitementDesActeurs(monPath, em);

            for (Acteur acteur : mesActeurs){
                em.persist(acteur);
            }

        } else if (monPath.contains("realisateurs.csv")) {

            /**
             * Première partie pour insérer les LieuxNaissance
             */
            List<LieuNaissance> mesLieuNaissances = csvService.traitementDesLieuNaissance(monPath);

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

            /**
             * Deuxième partie pour insérer les Réalisateurs
             */
            List<Personne> mesRealisateurs = csvService.traitementDesRealisateurs(monPath, em);

            for (Personne personne : mesRealisateurs){
                em.persist(personne);
            }
        } else {
            System.out.println("coucou");
        }

        transaction.commit();
        //String[] mesValeurs = csvService.traitementDesFilms(monPath);
        //System.out.println(Arrays.toString(mesValeurs));
    }
}