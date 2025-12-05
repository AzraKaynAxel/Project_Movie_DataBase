package fr.diginamic.service;

import fr.diginamic.entites.Acteur;
import fr.diginamic.entites.Film;
import fr.diginamic.entites.Genre;
import fr.diginamic.entites.Pays;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy");
        List<Film> films = new ArrayList<>();

        for (int i = iMin; i < iMax; i++) {
            String[] values = lines.get(i).split(";");

            System.out.println(Arrays.toString(values));
            Film film = new Film();
            film.setId(values[0]);
            film.setNom(values[1]);
            film.setDateSortie(LocalDate.parse(values[2], pattern));
            film.setRating(Float.parseFloat(values[3]));
            film.setUrl(values[4]);
            film.setLieuTournage(values[5]);

            films.add(film);
        }
        return films;
    }

    public void traitementDesActeurs(String monPath) {
    }
}