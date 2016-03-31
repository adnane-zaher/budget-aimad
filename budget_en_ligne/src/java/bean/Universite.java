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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hamid
 */
@Entity
public class Universite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String logo;

    @OneToMany(mappedBy = "universite")
    private List<Faculte> facultes;

    @OneToOne
    private User president;

    @OneToOne
    private BudgetUniversite budgetUniversite;

    @OneToOne(mappedBy = "universite")
    private AffectationUniversite affectationUniversite;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Faculte> getFacultes() {
        if (facultes == null) {
            facultes = new ArrayList();
        }
        return facultes;
    }

    public void setFacultes(List<Faculte> facultes) {
        this.facultes = facultes;
    }

    public User getPresident() {
        if (president == null) {
            president = new User();
        }
        return president;
    }

    public void setPresident(User president) {
        this.president = president;
    }

    public BudgetUniversite getBudgetUniversite() {
        if (budgetUniversite == null) {
            budgetUniversite = new BudgetUniversite();
        }
        return budgetUniversite;
    }

    public void setBudgetUniversite(BudgetUniversite budgetUniversite) {
        this.budgetUniversite = budgetUniversite;
    }

    public AffectationUniversite getAffectationUniversite() {
        if (affectationUniversite == null) {
            affectationUniversite = new AffectationUniversite();
        }
        return affectationUniversite;
    }

    public void setAffectationUniversite(AffectationUniversite affectationUniversite) {
        this.affectationUniversite = affectationUniversite;
    }

    //******************************************************
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universite)) {
            return false;
        }
        Universite other = (Universite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Universite[ id=" + id + " ]";
    }

}
