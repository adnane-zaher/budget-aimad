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
public class SyntheseByEntiteAdministratif implements Serializable {
   

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "syntheseByEntiteAdministratif")
    private List<SyntheseByEntiteAdministratifItem> syntheseByEntiteAdministratifItem;
//*************************************************************************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SyntheseByEntiteAdministratifItem> getSyntheseByEntiteAdministratifItem() {
        if (syntheseByEntiteAdministratifItem == null) {
            syntheseByEntiteAdministratifItem = new ArrayList<>();
        }
        return syntheseByEntiteAdministratifItem;
    }

    public void setSyntheseByEntiteAdministratifItem(List<SyntheseByEntiteAdministratifItem> syntheseByEntiteAdministratifItem) {
        this.syntheseByEntiteAdministratifItem = syntheseByEntiteAdministratifItem;
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
        if (!(object instanceof SyntheseByEntiteAdministratif)) {
            return false;
        }
        SyntheseByEntiteAdministratif other = (SyntheseByEntiteAdministratif) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.SyntheseByDepartement[ id=" + id + " ]";
    }

}
