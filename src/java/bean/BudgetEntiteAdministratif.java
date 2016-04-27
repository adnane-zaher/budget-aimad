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
public class BudgetEntiteAdministratif extends Budget {

    @OneToMany(mappedBy = "budgetEntiteAdministratif")
    private List<BudgetEntiteAdministratifItem> budgetEntiteAdministratifItems;

    @ManyToOne
    private EntiteAdministratif entiteAdministratif;
    @OneToOne
    private SyntheseByEntiteAdministratif syntheseByEntiteAdministratif;

    //*******************************************************
    public EntiteAdministratif getEntiteAdministratif() {
        return entiteAdministratif;
    }

    public void setEntiteAdministratif(EntiteAdministratif entiteAdministratif) {
        this.entiteAdministratif = entiteAdministratif;
    }

    public SyntheseByEntiteAdministratif getSyntheseByEntiteAdministratif() {
        return syntheseByEntiteAdministratif;
    }

    public void setSyntheseByEntiteAdministratif(SyntheseByEntiteAdministratif syntheseByEntiteAdministratif) {
        this.syntheseByEntiteAdministratif = syntheseByEntiteAdministratif;
    }

    public List<BudgetEntiteAdministratifItem> getBudgetEntiteAdministratifItems() {
        return budgetEntiteAdministratifItems;
    }

    public void setBudgetEntiteAdministratifItems(List<BudgetEntiteAdministratifItem> budgetEntiteAdministratifItems) {
        this.budgetEntiteAdministratifItems = budgetEntiteAdministratifItems;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final BudgetEntiteAdministratif other = (BudgetEntiteAdministratif) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
