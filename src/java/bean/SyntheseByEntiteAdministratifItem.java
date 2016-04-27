/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Adnane
 */
@Entity
public class SyntheseByEntiteAdministratifItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SyntheseByEntiteAdministratif syntheseByEntiteAdministratif;
    private Double montantPaye;
    private Double montantEngage;
    private Double montantAffecte;
    

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SyntheseByEntiteAdministratif getSyntheseByEntiteAdministratif() {
        if (syntheseByEntiteAdministratif == null) {
            syntheseByEntiteAdministratif = new SyntheseByEntiteAdministratif();
        }
        return syntheseByEntiteAdministratif;
    }

    public void setSyntheseByDepartement(SyntheseByEntiteAdministratif syntheseByEntiteAdministratif) {
        this.syntheseByEntiteAdministratif = syntheseByEntiteAdministratif;
    }

    public Double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Double getMontantEngage() {
        return montantEngage;
    }

    public void setMontantEngage(Double montantEngage) {
        this.montantEngage = montantEngage;
    }

    public Double getMontantAffecte() {
        return montantAffecte;
    }

    public void setMontantAffecte(Double montantAffecte) {
        this.montantAffecte = montantAffecte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SyntheseByEntiteAdministratifItem)) {
            return false;
        }
        SyntheseByEntiteAdministratifItem other = (SyntheseByEntiteAdministratifItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.SyntheseByDepartementItem[ id=" + id + " ]";
    }

}
