package fr.diginamic.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="film")
public class Film {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "nom", length = 50, nullable = false, unique = true)
    private String nom;

    @Column(name = "date_sortie", nullable = false)
    private String dateSortie;

    @Column(name = "rating", nullable = true, precision=4, scale=2)
    private float rating;

    @Column(name = "url", length = 255, nullable = true)
    private String url;

    @Column(name="lieu_tournage", length=50, nullable = false)
    private String lieuTournage;

    @Column(name="resume", columnDefinition="TEXT", length=255, nullable = false)
    private String resume;

    @ManyToMany
    @JoinTable(name="fil_pers", joinColumns=@JoinColumn(name="id_fil", referencedColumnName="id"), inverseJoinColumns=@JoinColumn(name="id_pers", referencedColumnName="id"))
    private Set<Personne> realisteurs;

    @ManyToMany
    @JoinTable(name="fil_ger", joinColumns=@JoinColumn(name="id_fil", referencedColumnName="id"), inverseJoinColumns=@JoinColumn(name="id_ger", referencedColumnName="id"))
    private Set<Genre> genres;

    @ManyToOne
    @JoinColumn(name="id_pays", nullable=false)
    private Pays pays;

    @ManyToOne
    @JoinColumn(name="id_langue", nullable = false)
    private Langue langue;

    @OneToMany(mappedBy="film")
    private List<Role> roles = new ArrayList<Role>();


    /**
     * Constructor for: Film
     */
    public Film() {}

    /**
     * Constructor for: Film
     *
     * @param id
     * @param nom
     * @param anneeSortie
     * @param rating
     * @param url
     * @param lieuTournage
     * @param genres
     * @param langue
     * @param resume
     * @param pays
     */
    public Film(String id, String nom, String dateSortie, float rating, String url, String lieuTournage, Set<Genre> genres, Langue langue, String resume, Pays pays) {
        this.id = id;
        this.nom = nom;
        this.dateSortie = dateSortie;
        this.rating = rating;
        this.url = url;
        this.lieuTournage = lieuTournage;
        this.genres = genres;
        this.langue = langue;
        this.resume = resume;
        this.pays = pays;
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
     * Getter for dateSortie
     *
     * @return dateSortie
     */
    public String getDateSortie() {
        return dateSortie;
    }

    /**
     * Setter for dateSortie
     *
     * @param anneeSortie
     */
    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    /**
     * Getter for rating
     *
     * @return rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Setter for rating
     *
     * @param rating
     */
    public void setRating(float rating) {
        this.rating = rating;
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
     * Getter for lieuTournage
     *
     * @return lieuTournage
     */
    public String getLieuTournage() {
        return lieuTournage;
    }

    /**
     * Setter for lieuTournage
     *
     * @param lieuTournage
     */
    public void setLieuTournage(String lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    /**
     * Getter for resume
     *
     * @return resume
     */
    public String getResume() {
        return resume;
    }

    /**
     * Setter for resume
     *
     * @param resume
     */
    public void setResume(String resume) {
        this.resume = resume;
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
     * Getter for langue
     *
     * @return langue
     */
    public Langue getLangue() {
        return langue;
    }

    /**
     * Setter for langue
     *
     * @param langue
     */
    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    /**
     * Getter for roles
     *
     * @return roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Setter for roles
     *
     * @param roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Getter for realisteurs
     *
     * @return realisteurs
     */
    public Set<Personne> getRealisteurs() {
        return realisteurs;
    }

    /**
     * Getter for genres
     *
     * @return genres
     */
    public Set<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Film: " +
                "id= '" + id + '\'' +
                ", nom= '" + nom + '\'' +
                ", dateSortie= " + dateSortie +
                ", resume= '" + resume + '\'' +
                ", genres= " + genres +
                ", url= '" + url + '\'' +
                ", rating= " + rating +
                ", lieuTournage= '" + lieuTournage + '\'' +
                ", pays= " + pays +
                ", realisteurs= " + realisteurs +
                ", roles= " + roles +
                ", langue= " + langue;
    }
}