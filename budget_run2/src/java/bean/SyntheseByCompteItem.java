/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Adnane
 */
@Entity
public class SyntheseByCompteItem extends CompteItem{
    @ManyToOne
    private SyntheseByCompte syntheseByCompte;
//*********************************************************
    public SyntheseByCompte getSyntheseByCompte() {
        if(syntheseByCompte == null){
            syntheseByCompte = new SyntheseByCompte();
        }
        return syntheseByCompte;
    }

    public void setSyntheseByCompte(SyntheseByCompte syntheseByCompte) {
        this.syntheseByCompte = syntheseByCompte;
    }
    
    
    
}
