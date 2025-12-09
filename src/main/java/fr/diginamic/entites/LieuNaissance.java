package fr.diginamic.entites;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lieu_naissance")
public class LieuNaissance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="localisation", length=255, nullable=false)
    private String localisation;


    /**
     * Constructor for: LieuNaissance
     */
    public LieuNaissance() {}

    /**
     * Constructor for: LieuNaissance
     *
     * @param localisation
     */
    public LieuNaissance(String localisation) {
        this.localisation = localisation;
    }

    /**
     * Getter for Localisation
     *
     * @return localisation
     */
    public String getLocalisation() {
        return localisation;
    }

    /**
     * Setter for Localisation
     *
     * @param localisation
     */
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return "LieuNaissance: " +
                "id= " + id +
                ", localisation= '" + localisation + '\'';
    }
}
