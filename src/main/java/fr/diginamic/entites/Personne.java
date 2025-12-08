package fr.diginamic.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="personne")
/**
 * @Inheritance pour faire de personne et acteur 2 tables différentes
 */
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Personne {

    @Id
    private String id;

    @Column(name = "identite", length = 50, nullable = false)
    private String identite;

    @Column(name = "date_anniversaire", nullable = false)
    private LocalDate dateAnniversaire;

    @Column(name = "lieu_naissance", length = 50, nullable = false)
    private String lieuNaissance;

    @Column(name = "url", length = 255, nullable = true)
    private String url;

    @ManyToOne
    @JoinColumn(name="id_pays")
    private Pays pays;

    @ManyToMany
    @JoinTable(name="fil_pers", joinColumns=@JoinColumn(name="id_pers", referencedColumnName="id"), inverseJoinColumns=@JoinColumn(name="id_fil", referencedColumnName="id"))
    private Set<Film> films;


    /**
     * Constructor for: Personne
     */
    public Personne() {}

    /**
     * Constructor for: Personne
     *
     * @param id
     * @param identite
     * @param dateAnniversaire
     * @param lieuNaissance
     * @param pays
     * @param url
     */
    public Personne(String id, String identite, LocalDate dateAnniversaire, String lieuNaissance, Pays pays, String url) {
        this.id = id;
        this.identite = identite;
        this.dateAnniversaire = dateAnniversaire;
        this.lieuNaissance = lieuNaissance;
        this.pays = pays;
        this.url = url;
    }

    /**
     * Getter for dateAnniversaire
     *
     * @return dateAnniversaire
     */
    public LocalDate getDateAnniversaire() {
        return dateAnniversaire;
    }


    /**
     * Setter for dateAnniversaire
     *
     * @param dateAnniversaire
     */
    public void setDateAnniversaire(LocalDate dateAnniversaire) {
        this.dateAnniversaire = dateAnniversaire;
    }

    /**
     * Getter for id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for identite
     *
     * @return identite
     */
    public String getIdentite() {
        return identite;
    }

    /**
     * Setter for identite
     *
     * @param identite
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    /**
     * Getter for lieuNaissance
     *
     * @return lieuNaissance
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Setter for lieuNaissance
     *
     * @param lieuNaissance
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * Getter for pays
     *
     * @return pays
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Setter for pays
     *
     * @param pays
     */
    public void setPays(Pays pays) {
        this.pays = pays;
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

    /**
     * Getter for films
     *
     * @return films
     */
    public Set<Film> getFilms() {
        return films;
    }

    @Override
    public String toString() {
        return ", id= '" + id + '\'' +
                ", identite= '" + identite + '\'' +
                ", lieuNaissance= '" + lieuNaissance + '\'' +
                ", dateAnniversaire= " + dateAnniversaire +
                ", url='" + url + '\'' +
                ", pays=" + pays +
                ", films= " + films;
    }
}

