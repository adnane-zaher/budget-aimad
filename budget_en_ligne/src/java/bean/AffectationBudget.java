/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author hamid
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class AffectationBudget implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateValidation;
    private Date dateSignature;
    //relicat 

    private String commentaire ;
    @OneToOne
    private User responsableAffectation;
//
//    @OneToOne
//    private EntiteAdministratif entiteAdministratif; //ici on s'interesse qu'a l'affectation au entites au sein de notre faculte 

    @OneToMany(mappedBy = "affectationBudget")
    private List<AffectationBudgetItem> affectationBudgetItems;

    //****************************************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    public User getResponsableAffectation() {
        if (responsableAffectation == null) {
            responsableAffectation = new User();
        }
        return responsableAffectation;
    }

    public void setResponsableAffectation(User responsableAffectation) {
        this.responsableAffectation = responsableAffectation;
    }

//    public EntiteAdministratif getEntiteAdministratif() {
//        if (entiteAdministratif == null) {
//            entiteAdministratif = new EntiteAdministratif();
//        }
//        return entiteAdministratif;
//    }
//
//    public void setEntiteAdministratif(EntiteAdministratif entiteAdministratif) {
//        this.entiteAdministratif = entiteAdministratif;
//    }

    public List<AffectationBudgetItem> getAffectationBudgetItems() {
        if (affectationBudgetItems == null) {
            affectationBudgetItems = new ArrayList();
        }
        return affectationBudgetItems;
    }

    public void setAffectationBudgetItems(List<AffectationBudgetItem> affectationBudgetItems) {
        this.affectationBudgetItems = affectationBudgetItems;
    }
    
    //****************************************************

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AffectationBudget)) {
            return false;
        }
        AffectationBudget other = (AffectationBudget) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.AffectationBudget[ id=" + id + " ]";
    }

}
