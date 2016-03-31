/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author hamid
 */
@Entity
public class BudgetEntiteAdministratif extends Budget {

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "budgetEntiteAdministratif")
    private EntiteAdministratif entiteAdministratif;

    @OneToOne
    private AffectationBudget affectationBudget;

    //****************************************
    public EntiteAdministratif getEntiteAdministratif() {
        if (entiteAdministratif == null) {
            entiteAdministratif = new EntiteAdministratif();
        }
        return entiteAdministratif;
    }

    public void setEntiteAdministratif(EntiteAdministratif entiteAdministratif) {
        this.entiteAdministratif = entiteAdministratif;
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

    //*******************************************************
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
