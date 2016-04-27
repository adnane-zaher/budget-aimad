/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hamid
 */
@Entity
public class BudgetFaculte extends Budget {

    @ManyToOne
    private Faculte faculte;

    @OneToOne
    private SyntheseByCompte SyntheseByCompte;

    @OneToOne
   
    @OneToMany(mappedBy = "budgetFaculte")
    private List<BudgetFaculteItem> budgetFaculteItems;

    public SyntheseByCompte getSyntheseByCompte() {
        return SyntheseByCompte;
    }

    public void setSyntheseByCompte(SyntheseByCompte SyntheseByCompte) {
        this.SyntheseByCompte = SyntheseByCompte;
    }

   

    public List<BudgetFaculteItem> getBudgetFaculteItems() {
        return budgetFaculteItems;
    }

    //*************************************
    public void setBudgetFaculteItems(List<BudgetFaculteItem> budgetFaculteItems) {
        this.budgetFaculteItems = budgetFaculteItems;
    }

    public Faculte getFaculte() {
        return faculte;
    }

    public void setFaculte(Faculte faculte) {
        this.faculte = faculte;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BudgetFaculte other = (BudgetFaculte) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
