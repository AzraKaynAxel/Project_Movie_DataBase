package fr.diginamic.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pays")
public class Pays {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="nom", length=40, nullable = false)
    private String nom;

    @Column(name = "url", length = 255, nullable = true)
    private String url;


    /**
     * Constructor for: Pays
     */
    public Pays() {}

    /**
     * Constructor for: Pays
     *
     * @param nom
     * @param url
     */
    public Pays(int id, String nom, String url) {
        this.nom = nom;
        this.url = url;
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

    /**
     * Getter for url
     *
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter for url
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Pays: " +
                "id= " + id +
                ", nom= '" + nom + '\'' +
                ", url= '" + url + '\'';
    }
}
