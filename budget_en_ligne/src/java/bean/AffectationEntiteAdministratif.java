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
public class AffectationEntiteAdministratif extends AffectationBudget {

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "affectationEntiteAdministratif")
    private EntiteAdministratif entiteAdministratif;

    public EntiteAdministratif getEntiteAdministratif() {
        if (entiteAdministratif == null) {
            entiteAdministratif = new EntiteAdministratif();
        }
        return entiteAdministratif;
    }

    public void setEntiteAdministratif(EntiteAdministratif entiteAdministratif) {
        this.entiteAdministratif = entiteAdministratif;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.entiteAdministratif);
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
        final AffectationEntiteAdministratif other = (AffectationEntiteAdministratif) obj;
        if (!Objects.equals(this.entiteAdministratif, other.entiteAdministratif)) {
            return false;
        }
        return true;

    }

}
