package fr.diginamic.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="acteur")
public class Acteur extends Personne{

    @Column(name="taille_m", nullable=false, precision=3, scale=2)
    private float taille;


    /**
     * Constructor for: Acteur
     */
    public Acteur() {}

    /**
     * Constructor for: Acteur
     *
     * @param id
     * @param identite
     * @param dateAnniversaire
     * @param lieuNaissance
     * @param url
     * @param taille
     */
    public Acteur(String id, String identite, LocalDate dateAnniversaire, LieuNaissance lieuNaissance, String url, float taille) {
        super(id, identite, dateAnniversaire, lieuNaissance, url);
        this.taille = taille;
    }

    /**
     * Getter for taille
     *
     * @return taille
     */
    public float getTaille() {
        return taille;
    }

    /**
     * Setter for taille
     *
     * @param taille
     */
    public void setTaille(float taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "Acteur: " + super.toString() + " taille= " + taille;
    }
}
