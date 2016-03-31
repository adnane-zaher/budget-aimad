/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author hamid
 */
@Entity
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id; //le numero du compte d'etat 
    private String designation;
    private BigDecimal montantMax;  // le montant a ne pas deppaser 

    @OneToMany(mappedBy = "compte")
    private List<AffectationBudgetItem> affectationBudgetItems;

    //****************************************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getMontantMax() {
        if (montantMax == null) {
            montantMax = new BigDecimal(0);
        }
        return montantMax;
    }

    public void setMontantMax(BigDecimal montantMax) {
        this.montantMax = montantMax;
    }

    public List<AffectationBudgetItem> getAffectationBudgetItems() {
       if(affectationBudgetItems == null ){
           affectationBudgetItems = new ArrayList();
       }
        return affectationBudgetItems;
    }

    public void setAffectationBudgetItems(List<AffectationBudgetItem> affectationBudgetItems) {
        this.affectationBudgetItems = affectationBudgetItems;
    }

    //*******************************************
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Compte[ id=" + id + " ]";
    }

}
