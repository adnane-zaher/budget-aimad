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

/**
 *
 * @author hamid
 */
@Entity
public class AffectationBudgetItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal montantAffecte;
    private BigDecimal montantEngage;
    private BigDecimal montantPayer;

    @ManyToOne
    private AffectationBudget affectationBudget;

    @ManyToOne
    private Compte compte;

    //****************************************************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontantAffecte() {
        if (montantAffecte == null) {
            montantAffecte = new BigDecimal(0);
        }
        return montantAffecte;
    }

    public void setMontantAffecte(BigDecimal montantAffecte) {
        this.montantAffecte = montantAffecte;
    }

    public BigDecimal getMontantEngage() {
        if (montantEngage == null) {
            montantEngage = new BigDecimal(0);
        }
        return montantEngage;
    }

    public void setMontantEngage(BigDecimal montantEngage) {
        this.montantEngage = montantEngage;
    }

    public BigDecimal getMontantPayer() {
        if (montantPayer == null) {
            montantPayer = new BigDecimal(0);
        }
        return montantPayer;
    }

    public void setMontantPayer(BigDecimal montantPayer) {
        this.montantPayer = montantPayer;
    }

    public AffectationBudget getAffectationBudget() {
        if (affectationBudget == null) {
            affectationBudget = new AffectationBudget();
        }
        return affectationBudget;
    }

    public void setAffectationBudget(AffectationBudget affectationBudget) {
        this.affectationBudget = affectationBudget;
    }

    public Compte getCompte() {
        if (compte == null) {
            compte = new Compte();
        }
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    //*****************************************
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AffectationBudgetItem)) {
            return false;
        }
        AffectationBudgetItem other = (AffectationBudgetItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.AffectationBudgetItem[ id=" + id + " ]";
    }

}
