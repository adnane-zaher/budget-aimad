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
public class BudgetFaculte extends Budget {

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "budgetFaculte")
    private Faculte faculte;

    //******************************************
    public Faculte getFaculte() {
        if (faculte == null) {
            faculte = new Faculte();
        }
        return faculte;
    }

    public void setFaculte(Faculte faculte) {
        this.faculte = faculte;
    }
    //*************************************

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
