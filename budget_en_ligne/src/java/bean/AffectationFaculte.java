/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author hamid
 */
@Entity
public class AffectationFaculte extends AffectationBudget {

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "affectationFaculte")
    private Faculte faculte;

    public Faculte getFaculte() {
        if (faculte == null) {
            faculte = new Faculte();
        }
        return faculte;
    }

    public void setFaculte(Faculte faculte) {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.faculte);
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
        final AffectationFaculte other = (AffectationFaculte) obj;
        if (!Objects.equals(this.faculte, other.faculte)) {
            return false;
        }
        return true;
    }

}
