/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author hamid
 */
@Entity
public class EntiteAdministratif implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String logo;
    private String type; //que ca soit un {Laboratoite, Departement , Administration ...}
    private BigDecimal sommeInterieur;  //ce qui reste du budget Affecter 

    @ManyToOne
    private Faculte faculte;

    @OneToOne
    private User chef;

    @OneToOne
    private BudgetEntiteAdministratif budgetEntiteAdministratif;

    @OneToOne(mappedBy = "entiteAdministratif")
    private AffectationEntiteAdministratif affectationEntiteAdministratif;

    //***************************************************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getSommeInterieur() {
        if (sommeInterieur == null) {
            sommeInterieur = new BigDecimal(0);
        }
        return sommeInterieur;
    }

    public void setSommeInterieur(BigDecimal sommeInterieur) {
        this.sommeInterieur = sommeInterieur;
    }

    public Faculte getFaculte() {
        if (faculte == null) {
            faculte = new Faculte();
        }
        return faculte;
    }

    public void setFaculte(Faculte faculte) {
        this.faculte = faculte;
    }

    public User getChef() {
        if (chef == null) {
            chef = new User();
        }
        return chef;
    }

    public void setChef(User chef) {
        this.chef = chef;
    }

    public BudgetEntiteAdministratif getBudgetEntiteAdministratif() {
        if (budgetEntiteAdministratif == null) {
            budgetEntiteAdministratif = new BudgetEntiteAdministratif();
        }
        return budgetEntiteAdministratif;
    }

    public void setBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif) {
        this.budgetEntiteAdministratif = budgetEntiteAdministratif;
    }

    public AffectationEntiteAdministratif getAffectationEntiteAdministratif() {
        if (affectationEntiteAdministratif == null) {
            affectationEntiteAdministratif = new AffectationEntiteAdministratif();
        }
        return affectationEntiteAdministratif;
    }

    public void setAffectationEntiteAdministratif(AffectationEntiteAdministratif affectationEntiteAdministratif) {
        this.affectationEntiteAdministratif = affectationEntiteAdministratif;
    }

    //***********************************************************
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntiteAdministratif)) {
            return false;
        }
        EntiteAdministratif other = (EntiteAdministratif) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.EntiteAdministratif[ id=" + id + " ]";
    }

}
