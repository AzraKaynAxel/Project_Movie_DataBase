package fr.diginamic;

import fr.diginamic.services.CsvService;
import fr.diginamic.services.DataBaseService;

public class Runner {
    public static void main(String[] args) {

        CsvService csvService = new CsvService();
        DataBaseService dataBaseService = new DataBaseService();

        //dataBaseService.connexionDataBase();

        dataBaseService.insertionDataBase("D:/Cours_CDA/17 - Projet en équipe - développement d'une application JAVA avec JPA/Projet 4 - Internet Movie DataBase - niveau 3/pays.csv");
        dataBaseService.insertionDataBase("D:/Cours_CDA/17 - Projet en équipe - développement d'une application JAVA avec JPA/Projet 4 - Internet Movie DataBase - niveau 3/acteurs.csv");
        dataBaseService.insertionDataBase("D:/Cours_CDA/17 - Projet en équipe - développement d'une application JAVA avec JPA/Projet 4 - Internet Movie DataBase - niveau 3/realisateurs.csv");
        dataBaseService.insertionDataBase("D:/Cours_CDA/17 - Projet en équipe - développement d'une application JAVA avec JPA/Projet 4 - Internet Movie DataBase - niveau 3/films.csv");

        //csvService.traitementDesFilms("D:/Cours_CDA/17 - Projet en équipe - développement d'une application JAVA avec JPA/Projet 4 - Internet Movie DataBase - niveau 3/films.csv");
        //csvService.traitementDesActeurs("D:/Cours_CDA/17 - Projet en équipe - développement d'une application JAVA avec JPA/Projet 4 - Internet Movie DataBase - niveau 3/acteurs.csv");
        //csvService.traitementDesPays("D:/Cours_CDA/17 - Projet en équipe - développement d'une application JAVA avec JPA/Projet 4 - Internet Movie DataBase - niveau 3/pays.csv");
        //dataBaseService.insertionDataBase("D:/Cours_CDA/17 - Projet en équipe - développement d'une application JAVA avec JPA/Projet 4 - Internet Movie DataBase - niveau 3/films.csv");
    }
}
