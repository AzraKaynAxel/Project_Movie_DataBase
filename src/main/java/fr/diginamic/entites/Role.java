package fr.diginamic.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="personnage", length=50, nullable = false)
    private String personnage;

    @ManyToOne
    @JoinColumn(name="id_film")
    private Film film;

    @ManyToOne
    @JoinColumn(name="id_acteur")
    private Acteur acteur;


    /**
     * Constructor for: Role
     */
    public Role() {}

    /**
     * Constructor for: Role
     *
     * @param acteur
     * @param film
     * @param personnage
     */
    public Role(Acteur acteur, Film film, String personnage) {
        this.acteur = acteur;
        this.film = film;
        this.personnage = personnage;
    }

    /**
     * Getter for acteur
     *
     * @return acteur
     */
    public Acteur getActeur() {
        return acteur;
    }

    /**
     * Setter for acteur
     *
     * @param acteur
     */
    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    /**
     * Getter for film
     *
     * @return film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Setter for film
     *
     * @param film
     */
    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Getter for id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for personnage
     *
     * @return personnage
     */
    public String getPersonnage() {
        return personnage;
    }

    /**
     * Setter for personnage
     *
     * @param personnage
     */
    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

    @Override
    public String toString() {
        return "Role: " +
                "id= " + id +
                ", acteur= " + acteur +
                ", personnage= '" + personnage + '\'' +
                ", film= " + film;
    }
}