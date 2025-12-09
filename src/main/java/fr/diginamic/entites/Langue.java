package fr.diginamic.entites;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="langue")
public class Langue {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="nom", length=30, nullable = false)
    private String nom;

    @OneToMany(mappedBy="langue")
    private List<Film> films;

    public Langue () {}

    public Langue(String nom) {
        this.nom = nom;
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

    /**
     * Getter for films
     *
     * @return films
     */
    public List<Film> getFilms() {
        return films;
    }

    /**
     * Setter for films
     *
     * @param films
     */
    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Langue: " +
                "id= " + id +
                ", nom= '" + nom + '\'' +
                ", films= " + films;
    }
}
