/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Adnane
 */
@Entity
public class SyntheseByCompte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "syntheseByCompte")
    private List<SyntheseByCompteItem> syntheseByCompteItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SyntheseByCompteItem> getSyntheseByCompteItems() {
        if (syntheseByCompteItems == null) {
            syntheseByCompteItems = new ArrayList<>();
        }
        return syntheseByCompteItems;
    }

    public void setSyntheseByCompteItems(List<SyntheseByCompteItem> syntheseByCompteItems) {
        this.syntheseByCompteItems = syntheseByCompteItems;
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
        if (!(object instanceof SyntheseByCompte)) {
            return false;
        }
        SyntheseByCompte other = (SyntheseByCompte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.SyntheseByCompte[ id=" + id + " ]";
    }

}
