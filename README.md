# Project_Movie_DataBase 🌱

## Description 📋

**Résumé** 📝: Ce dépôt contient un projet Maven JPA pour gérer une base de données de films avec Hibernate et JPA.

**Prérequis** ✅:
- **Code**: structure de projet Java/Maven prête.
- **Configuration du pom.xml**: dépendances Hibernate, MariaDB JDBC et JUnit Jupiter configurées.
- **Configuration JPA**: `persistence.xml` présent dans `src/main/resources/META-INF` (et copié dans `target/classes/META-INF` après compilation) ⚙️.

## Détails des étapes à réalisées 🚧 :

### Réalisation sur la branche *`class/CreationEntites`*
- **1) Implémentation des classes entités** 🧩:
    - définition des classes abstraites
    - classes concrètes
    - Chaque entité utilise des annotations JPA pour la persistance (`@Entity`, `@Inheritance`, `@Id`, etc.),

*voir `src/main/java/fr/diginamic/entites`* 🏦.

### Réalisation sur la branche *`feature/ConnextionBDD`*
- **2) Implémentation du service de base de données** 🗄️:
    - `DataBaseService.java` - Service principal pour gérer la connexion à la base de données
      - Méthodes: `connexionDataBase()`, `insertionDataBase(String monPath)`, `lectureDeFichierCSV(String monPath)`.

*voir `src/main/java/fr/diginamic/service`* 📝.

### Réalisation sur la branche *`feature/TraitementFichier`*
- **3) Implémentation du traitement de fichier** 📄:
    - Lecture et traitement de fichiers CSV
    - Méthodes : 
      - `lectureDeFichierCSV(String monPath)` - Traitement complet des fichiers de données.

*voir `src/main/java/fr/diginamic/service`* 📝.

## Tests 🧪:
 - `DataBaseServiceTest.java` - Tests du service de base de données avec JUnit Jupiter
    
 - Utilisation des annotations JUnit : `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll`
    - Framework de test : JUnit Jupiter 5.10.2

*voir `src/test/java/fr/diginamic/service`* .