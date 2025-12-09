package fr.diginamic.services;

import fr.diginamic.entites.Acteur;
import fr.diginamic.entites.Film;
import fr.diginamic.entites.Genre;
import fr.diginamic.entites.Langue;
import fr.diginamic.entites.LieuNaissance;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.Personne;

import javax.crypto.spec.PSource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CsvService {

    private Path path;
    List<String> lines;
    int iMin = 0; // pour tester les listes petit à petit
    int iMax = 300;

    private static final String REQUETE_RECUP_LIEU_NAISSANCE = "SELECT e FROM LieuNaissance e WHERE e.localisation = :value";
    private static final String REQUETE_RECUP_PAYS = "SELECT e FROM Pays e WHERE e.nom = :value";
    private static final String REQUETE_RECUP_GENRE = "SELECT e FROM Genre e WHERE e.nom = :value";
    private static final String REQUETE_RECUP_LANGUE = "SELECT e FROM Langue e WHERE e.nom = :value";

    public void lectureDeFichierCSV(String monPath) {
        Path pathOrigin = Paths.get(monPath);

        boolean exists = Files.exists(pathOrigin);

        if (exists) {
            try {
                lines = Files.readAllLines(pathOrigin, StandardCharsets.UTF_8);
                lines.remove(0);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public List<Film> traitementDesFilms(String monPath, EntityManager em) {
        lectureDeFichierCSV(monPath);

        // Notre date dans le fichier est ex: 1964 donc pas besoin de faire attention à la case
        //DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy");
        List<Film> films = new ArrayList<>();

        for (int i = iMin; i < 1000; i++) {
            String[] values = lines.get(i).split(";", 10);
            String[] tabGenre = values[6].trim().split(",");


            //LieuNaissance lieuNaissanceResult = selectLieuNaissance.getSingleResult();

            Film film = new Film();

            for (int j = 0; j < tabGenre.length; j++) {
                TypedQuery<Genre> selectGenres = em.createQuery(REQUETE_RECUP_GENRE, Genre.class);
                selectGenres.setParameter("value", tabGenre[j]);
                Genre genre = selectGenres.getSingleResult();
                film.getGenres().add(genre);
            }

            if (!values[3].isEmpty()) {
                film.setRating(Float.parseFloat(values[3]));
            }

            if (!values[7].isEmpty()) {
                TypedQuery<Langue> selectLangue = em.createQuery(REQUETE_RECUP_LANGUE, Langue.class);
                selectLangue.setParameter("value", values[7]);
                Langue langue = selectLangue.getSingleResult();
                film.setLangue(langue);
            } else {
                film.setLangue(null);
            }

            if (!values[9].isEmpty()) {
                TypedQuery<Pays> selectPays = em.createQuery(REQUETE_RECUP_PAYS, Pays.class);
                selectPays.setParameter("value", values[9]);

                System.out.println("-".repeat(100));
                System.out.println(Arrays.toString(values));
                System.out.println("-".repeat(100));

                Pays pays = selectPays.getSingleResult();

                System.out.println("-".repeat(100));
                System.out.println(pays);
                System.out.println("-".repeat(100));

                film.setPays(pays);
            } else {
                film.setPays(null);
            }


            film.setId(values[0]);
            film.setNom(values[1]);
            film.setDateSortie(values[2]);
            film.setUrl(values[4]);
            film.setLieuTournage(values[5]);
            film.setResume(values[8]);

            System.out.println(film.toString());

            films.add(film);
        }

        return films;
    }

    /**
     * @param monPath
     * @param em
     * @return
     *
     * Cette méthode à pour objectif de retourner une liste d'intance d'Acteur avec les propriétés
     */
    public List<Acteur> traitementDesActeurs(String monPath, EntityManager em) {
        lectureDeFichierCSV(monPath);

        /**
         *  DateTimeFormatterBuilder permet de pouvoir utiliser :
         *  parseCaseInsensitive() (ceci permert de faire attention à la case sinon risque d'avoir une exception sur le mois de l'année en tout lettre)
         *  appendPattern prend l'écriture présente dans le doc (attention de prendre la même).
         *  toFormatter car notre date est en anglaise.
         *
         *  Cei est valable pour une date en comme ex: "March 15 1954", "MARCH 15 1954", "march 15 1954".
         */
        //DateTimeFormatter pattern = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMMM d yyyy").toFormatter(Locale.ENGLISH);

        List<Acteur> acteurs = new ArrayList<>();

        for (int i = iMin; i < lines.size(); i++) {
            String[] values = lines.get(i).split(";");


            Acteur acteur = new Acteur();

            acteur.setId(values[0]);
            acteur.setIdentite(values[1]);
            acteur.setDateAnniversaire(values[2]);

            if (!values[3].trim().isEmpty()) {
                TypedQuery<LieuNaissance> selectLieuNaissance = em.createQuery(REQUETE_RECUP_LIEU_NAISSANCE, LieuNaissance.class);
                selectLieuNaissance.setParameter("value", values[3]);
                LieuNaissance lieuNaissanceResult = selectLieuNaissance.getSingleResult();
                acteur.setLieuNaissance(lieuNaissanceResult);
            }

            if (!values[4].isEmpty()) {
                acteur.setTaille(Float.parseFloat(values[4].replaceAll("[^\\d.,]", "").replace(",", ".")));
            }

            acteur.setUrl(values[5]);
            acteurs.add(acteur);
        }
        return acteurs;
    }

    /**
     * @param monPath
     * @param em
     * @return
     *
     * Cette méthode à pour objectif de retourner une liste d'intance de Réalisateur avec les propriétés
     */
    public List<Personne> traitementDesRealisateurs (String monPath, EntityManager em) {
        lectureDeFichierCSV(monPath);

        /**
         *  DateTimeFormatterBuilder permet de pouvoir utiliser :
         *  parseCaseInsensitive() (ceci permert de faire attention à la case sinon risque d'avoir une exception sur le mois de l'année en tout lettre)
         *  appendPattern prend l'écriture présente dans le doc (attention de prendre la même).
         *  toFormatter car notre date est en anglaise.
         *
         *  Cei est valable pour une date en comme ex: "March 15 1954", "MARCH 15 1954", "march 15 1954".
         */
        //DateTimeFormatter pattern = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMMM d yyyy").toFormatter(Locale.ENGLISH);

        //List<Personne> realisateurs = new ArrayList<>();
        HashMap<String, Personne> realisateurs = new HashMap<>();

        for (int i = iMin; i < lines.size(); i++) {
            String[] values = lines.get(i).split(";");


            if (!realisateurs.containsKey(values[0].trim()) && !values[0].trim().isEmpty()) {
                Personne personne = new Personne();
                personne.setId(values[0]);
                personne.setIdentite(values[1]);
                personne.setDateAnniversaire(values[2]);

                if (!values[3].trim().isEmpty()) {
                    TypedQuery<LieuNaissance> selectLieuNaissance = em.createQuery(REQUETE_RECUP_LIEU_NAISSANCE, LieuNaissance.class);
                    selectLieuNaissance.setParameter("value", values[3]);
                    LieuNaissance lieuNaissanceResult = selectLieuNaissance.getSingleResult();
                    personne.setLieuNaissance(lieuNaissanceResult);
                }

                personne.setUrl(values[4]);

                realisateurs.put(values[0].trim(), personne);
            }
        }
        return new ArrayList<>(realisateurs.values());
    }

    /**
     * @param monPath
     * @return
     *
     * Cette méthode à pour objectif de retourner une liste d'intance de Pays avec les propriétés
     */
    public List<Pays> traitementDesPays(String monPath) {
        lectureDeFichierCSV(monPath);
        List<Pays> mesPays = new ArrayList<>();

        for (int i = iMin; i < lines.size(); i++) {
            String[] values = lines.get(i).split(";");

            Pays pays = new Pays();
            pays.setNom(values[0]);
            pays.setUrl(values[1]);

            mesPays.add(pays);
        }
        return mesPays;
    }

    /**
     * @param monPath
     * @return
     *
     * Cette méthode à pour objectif de retourner une liste d'intance de LieuNaissance avec les propriétés
     *
     */
    public List<LieuNaissance> traitementDesLieuNaissance(String monPath) {
        lectureDeFichierCSV(monPath);
        List<LieuNaissance> lieuNaissances = new ArrayList<>();

        for (int i = iMin; i < lines.size(); i++) {
            String[] values = lines.get(i).split(";");

            LieuNaissance lieuNaissance = new LieuNaissance();

            if (!values[3].trim().isEmpty()) {
                lieuNaissance.setLocalisation(values[3].trim());
                lieuNaissances.add(lieuNaissance);
            }
        }
        return lieuNaissances;
    }

    /**
     * @param monPath
     * @return
     *
     * Cette méthode a pour objectif de retourner une liste d'intance de Genre avec les propriétés
     * Utilisation de HashMap pour ne pas avoir de doublons
     *
     */
    public List<Genre> traitementDesGenres(String monPath) {
        lectureDeFichierCSV(monPath);

        // Permet de faire une liste de Clé : Valeur
        HashMap<String, Genre> genres = new HashMap<>();

        for (int i = iMin; i < lines.size(); i++) {
            String[] values = lines.get(i).split(";");
            String[] tabGenres = values[6].trim().split(",");
            for (int j = 0; j < tabGenres.length ; j++) {
                if (!genres.containsKey(tabGenres[j]) && !tabGenres[j].isEmpty()) {
                    Genre genre = new Genre();
                    genre.setNom(tabGenres[j]);
                    genres.put(tabGenres[j], genre);
                }
            }
        }

        // Pour transformer une HashMap en List
        return new ArrayList<>(genres.values());
    }

    /**
     * @param monPath
     * @return
     *
     * Cette méthode a pour objectif de retourner une liste d'intance de Langue avec les propriétés
     * Utilisation de HashMap pour ne pas avoir de doublons
     */
    public List<Langue> traitementDesLangues(String monPath) {
        lectureDeFichierCSV(monPath);

        HashMap<String, Langue> langues = new HashMap<>();

        for (int i = iMin; i < lines.size(); i++) {
            String[] values = lines.get(i).split(";");

            if (!langues.containsKey(values[7].trim()) && !values[7].trim().isEmpty()) {
                Langue langue = new Langue();
                langue.setNom(values[7].trim());
                langues.put(values[7].trim(), langue);
            }
        }

        // Pour retourner une liste
        return new ArrayList<>(langues.values());
    }
}