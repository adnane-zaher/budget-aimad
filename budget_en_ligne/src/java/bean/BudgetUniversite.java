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
public class BudgetUniversite extends Budget {

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "budgetUniversite")
    private Universite universite;

    //*************************************
    public Universite getUniversite() {
        if (universite == null) {
            universite = new Universite();
        }
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    
    //****************************************
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final BudgetUniversite other = (BudgetUniversite) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
