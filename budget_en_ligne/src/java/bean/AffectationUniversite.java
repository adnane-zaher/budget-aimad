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
public class AffectationUniversite extends AffectationBudget {

    private static final long serialVersionUID = 1L;

    @OneToOne
    private Universite universite;

    public Universite getUniversite() {
        if (universite == null) {
            universite = new Universite();
        }
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.universite);
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
        final AffectationUniversite other = (AffectationUniversite) obj;
        if (!Objects.equals(this.universite, other.universite)) {
            return false;
        }
        return true;
    }

    
    
   
}
