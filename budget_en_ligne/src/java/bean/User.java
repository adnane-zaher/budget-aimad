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
import javax.persistence.OneToOne;

/**
 *
 * @author hamid
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;
    private String type; // que ca soit un {chefDepartement , doyen , president ...}

    @OneToOne(mappedBy = "chef")
    private EntiteAdministratif entiteAdministratif;

    @OneToOne(mappedBy = "doyen")
    private Faculte faculte;

    @OneToOne(mappedBy = "president")
    private Universite universite;

    //**************************************************************
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EntiteAdministratif getEntiteAdministratif() {
        if (entiteAdministratif == null) {
            entiteAdministratif = new EntiteAdministratif();
        }
        return entiteAdministratif;
    }

    public void setEntiteAdministratif(EntiteAdministratif entiteAdministratif) {
        this.entiteAdministratif = entiteAdministratif;
    }

    public Faculte getFaculte() {
        if (faculte == null) {
            faculte = new Faculte();
        }
        return faculte;
    }

    public void setFaculte(Faculte faculte) {
        this.faculte = faculte;
    }

    public Universite getUniversite() {
        if (universite == null) {
            universite = new Universite();
        }
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
    
    //****************************************************************

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.User[ id=" + id + " ]";
    }

}
