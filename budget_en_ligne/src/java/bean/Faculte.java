/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hamid
 */
@Entity
public class Faculte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String logo;

    @ManyToOne
    private Universite universite;

    @OneToMany(mappedBy = "faculte")
    private List<EntiteAdministratif> entites;

    @OneToOne
    private User doyen;

    @OneToOne
    private BudgetFaculte budgetFaculte;

    @OneToOne(mappedBy = "faculte")
    private AffectationFaculte affectationFaculte;

    //******************************************
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Universite getUniversite() {
        if (universite == null) {
            universite = new Universite();
        }
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public List<EntiteAdministratif> getEntites() {
        if (entites == null) {
            entites = new ArrayList();
        }
        return entites;
    }

    public void setEntites(List<EntiteAdministratif> entites) {
        this.entites = entites;
    }

    public User getDoyen() {
        if (doyen == null) {
            doyen = new User();
        }
        return doyen;
    }

    public void setDoyen(User doyen) {
        this.doyen = doyen;
    }

    public BudgetFaculte getBudgetFaculte() {
        if (budgetFaculte == null) {
            budgetFaculte = new BudgetFaculte();
        }
        return budgetFaculte;
    }

    public void setBudgetFaculte(BudgetFaculte budgetFaculte) {
        this.budgetFaculte = budgetFaculte;
    }

    public AffectationFaculte getAffectationFaculte() {
        if (affectationFaculte == null) {
            affectationFaculte = new AffectationFaculte();
        }
        return affectationFaculte;
    }

    public void setAffectationFaculte(AffectationFaculte affectationFaculte) {
        this.affectationFaculte = affectationFaculte;
    }

    //********************************************************   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faculte)) {
            return false;
        }
        Faculte other = (Faculte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Faculte[ id=" + id + " ]";
    }

}
