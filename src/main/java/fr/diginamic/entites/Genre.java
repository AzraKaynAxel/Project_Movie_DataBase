package fr.diginamic.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="genre")
public class Genre {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="nom", length=30, nullable = false)
    private String nom;

    @ManyToMany
    @JoinTable(name="fil_ger", joinColumns=@JoinColumn(name="id_ger", referencedColumnName="id"), inverseJoinColumns=@JoinColumn(name="id_fil", referencedColumnName="id"))
    private Set<Film> films;


    /**
     * Constructor for: Genre
     */
    public Genre() {}

    /**
     * Constructor for: Genre
     *
     * @param films
     * @param nom
     */
    public Genre(Set<Film> films, String nom) {
        this.films = films;
        this.nom = nom;
    }

    /**
     * Getter for films
     *
     * @return films
     */
    public Set<Film> getFilms() {
        return films;
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
     * Getter for nom
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter for nom
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Genre: " +
                "films= " + films +
                ", id= " + id +
                ", nom= '" + nom + '\'';
    }
}
