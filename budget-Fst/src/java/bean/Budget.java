/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static java.time.OffsetTime.now;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author hamid
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Budget implements Serializable {

    protected static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    protected Double montantAffecte; // hiya l flouss li t affectat f t=0
    protected Double montantEngage;
    protected Double montantPaye;
    protected Double relicatEengage; // howa affecte - engage
    protected Double relicatPaye; // affecte - paye 

    protected Double creditOuvert; // dakechi li chate mn les anciens budget
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAffectation;

    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date dateValidation;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date dateSignature;
    protected String commentaire;
    protected String annee;
    @OneToOne
    protected User responsableAffectation;
    //******************************************************

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

    public String getAnnee() {
      return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    //**********************************************
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontantAffecte() {
        return montantAffecte;
    }

    public void setMontantAffecte(Double montantAffecte) {
        this.montantAffecte = montantAffecte;
    }

    public Double getMontantEngage() {
        return montantEngage;
    }

    public void setMontantEngage(Double montantEngage) {
        this.montantEngage = montantEngage;
    }

    public Double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Double getRelicatEengage() {
        return relicatEengage;
    }

    public void setRelicatEengage(Double relicatEengage) {
        this.relicatEengage = relicatEengage;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Double getCreditOuvert() {
        return creditOuvert;
    }

    public void setCreditOuvert(Double creditOuvert) {
        this.creditOuvert = creditOuvert;
    }

    public Double getRelicatPaye() {
        return relicatPaye;
    }

    public void setRelicatPaye(Double relicatPaye) {
        this.relicatPaye = relicatPaye;
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
        if (!(object instanceof Budget)) {
            return false;
        }
        Budget other = (Budget) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Budget[ id=" + id + " ]";
    }

}
