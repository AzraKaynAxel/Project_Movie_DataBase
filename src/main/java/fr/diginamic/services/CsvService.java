package fr.diginamic.services;

import fr.diginamic.entites.Acteur;
import fr.diginamic.entites.Film;
import fr.diginamic.entites.LieuNaissance;
import fr.diginamic.entites.Pays;

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
import java.util.List;
import java.util.Locale;

public class CsvService {

    private Path path;
    List<String> lines;
    int iMin = 0; // pour tester les listes petit à petit
    int iMax = 3;

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

    public List<Film> traitementDesFilms(String monPath) {
        lectureDeFichierCSV(monPath);

        // Notre date dans le fichier est ex: 1964 donc pas besoin de faire attention à la case
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy");
        List<Film> films = new ArrayList<>();

        for (int i = iMin; i < iMax; i++) {
            String[] values = lines.get(i).split(";");

            System.out.println(Arrays.toString(values));
            Film film = new Film();
            film.setId(values[0]);
            film.setNom(values[1]);
            //film.setDateSortie(LocalDate.parse(values[2], pattern));
            film.setRating(Float.parseFloat(values[3]));
            film.setUrl(values[4]);
            film.setLieuTournage(values[5]);

            films.add(film);
        }
        return films;
    }


    /**
     * @param monPath
     * @return
     *
     * Cette méthode à pour objectif de retourner une liste d'intance d'acteur avec les propriétés
     * Objectif de na pas avoir de duplication de données.
     *
     */
    public void traitementDesActeurs(String monPath) {
        lectureDeFichierCSV(monPath);

        /**
         *  DateTimeFormatterBuilder permet de pouvoir utiliser :
         *  parseCaseInsensitive() (ceci permert de faire attention à la case sinon risque d'avoir une exception sur le mois de l'année en tout lettre)
         *  appendPattern prend l'écriture présente dans le doc (attention de prendre la même).
         *  toFormatter car notre date est en anglaise.
         *
         *  Cei est valable pour une date en comme ex: "March 15 1954", "MARCH 15 1954", "march 15 1954".
         */

        DateTimeFormatter pattern = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMMM d yyyy").toFormatter(Locale.ENGLISH);

        List<Acteur> acteurs = new ArrayList<>();

        for (int i = iMin; i < iMax; i++) {
            String[] values = lines.get(i).split(";");
            /*String[] naissance = values[3].split(",");
            StringBuilder monNouveauLieux = new StringBuilder();

            for (int j = 0; j < naissance.length - 1; j++) {
                if (j < naissance.length - 2) {
                    monNouveauLieux.append(naissance[j].trim() + ", ");
                } else  {
                    monNouveauLieux.append(naissance[j].trim());
                }
            }*/

            String dateString = values[2];
            LocalDate date = LocalDate.parse(dateString.trim(), pattern);
            LieuNaissance lieuNaissance = new LieuNaissance();
            lieuNaissance.setLocalisation(values[3]);


            Acteur acteur = new Acteur();

            acteur.setId(values[0]);
            acteur.setIdentite(values[1]);
            acteur.setDateAnniversaire(date);
            acteur.setLieuNaissance(lieuNaissance);
            acteur.setTaille(Float.parseFloat(values[4].replace(" m", "")));
            acteur.setUrl(values[5]);
            acteurs.add(acteur);
            //System.out.println(acteurs);
        }
        System.out.println(acteurs);
    }

    /**
     * @param monPath
     * @return
     */
    public List<Pays> traitementDesPays(String monPath) {
        lectureDeFichierCSV(monPath);
        List<Pays> mesPays = new ArrayList<>();

        for (int i = iMin; i < iMax; i++) {
            String[] values = lines.get(i).split(";");

            Pays pays = new Pays();
            pays.setNom(values[0]);
            pays.setUrl(values[1]);

            mesPays.add(pays);
            System.out.println(mesPays);
        }
        return mesPays;
    }
}