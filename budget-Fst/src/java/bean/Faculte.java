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
 * @author hamid
 */
@Entity
public class Faculte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String logo;

    @OneToMany(mappedBy = "faculte")
    private List<EntiteAdministratif> entiteAdministratifs;

    @OneToMany(mappedBy = "faculte")
    private List<User> users;
    
   
    @OneToMany(mappedBy = "faculte")
    private List<BudgetFaculte> budgetFacultes;

    //******************************************

    public List<BudgetFaculte> getBudgetFacultes() {
        return budgetFacultes;
    }

    public void setBudgetFacultes(List<BudgetFaculte> budgetFacultes) {
        this.budgetFacultes = budgetFacultes;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<EntiteAdministratif> getEntiteAdministratifs() {
        if (entiteAdministratifs == null) {
            entiteAdministratifs = new ArrayList();
        }
        return entiteAdministratifs;
    }

    public void setEntiteAdministratifs(List<EntiteAdministratif> entiteAdministratifs) {
        this.entiteAdministratifs = entiteAdministratifs;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    //********************************************************   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faculte)) {
            return false;
        }
        Faculte other = (Faculte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Faculte[ id=" + id + " ]";
    }

}
