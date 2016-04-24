/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BudgetEntiteAdministratif;
import bean.EntiteAdministratif;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adnane
 */
@Stateless
public class BudgetEntiteAdministratifFacade extends AbstractFacade<BudgetEntiteAdministratif> {
    @PersistenceContext(unitName = "budget_run2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BudgetEntiteAdministratifFacade() {
        super(BudgetEntiteAdministratif.class);
    }
    
    public BudgetEntiteAdministratif findByAnneAndEntity(String annee, EntiteAdministratif entiteAdministratif){
       String requette ="SELECT ba FROM BudgetEntiteAdministratif ba WHERE ba.annee='"+annee+"' and ba.entiteAdministratif.id="+entiteAdministratif.getId();
        
        return (BudgetEntiteAdministratif) em.createQuery(requette).getSingleResult();
        
    }
    
    public List<BudgetEntiteAdministratif> findByEntity(EntiteAdministratif entiteAdministratif){
        String requette = "SELECT ba FROM BudgetEntiteAdministratif ba WHERE ba.entiteAdministratif.id="+entiteAdministratif.getId();
        return em.createQuery(requette).getResultList();
    }
}
