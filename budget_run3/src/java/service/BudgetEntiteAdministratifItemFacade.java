/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BudgetEntiteAdministratif;
import bean.BudgetEntiteAdministratifItem;
import bean.CompteItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adnane
 */
@Stateless
public class BudgetEntiteAdministratifItemFacade extends AbstractFacade<BudgetEntiteAdministratifItem> {
    @PersistenceContext(unitName = "budget_run2PU")
    private EntityManager em;
    @EJB 
    private BudgetEntiteAdministratifFacade budgetEntiteAdministratifFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BudgetEntiteAdministratifItemFacade() {
        super(BudgetEntiteAdministratifItem.class);
    }
    
    public void remove(BudgetEntiteAdministratifItem budgetEntiteAdministratifItem){
         BudgetEntiteAdministratif budgetEntiteAdministratif = budgetEntiteAdministratifFacade.find(budgetEntiteAdministratifItem.getBudgetEntiteAdministratif().getId());
         budgetEntiteAdministratif.setMontantEngage(budgetEntiteAdministratif.getMontantEngage() - budgetEntiteAdministratifItem.getCompteItem().getMontantAffecte());
         budgetEntiteAdministratif.setMontantPaye(budgetEntiteAdministratif.getMontantPaye()- budgetEntiteAdministratifItem.getCompteItem().getMontantPaye());
         budgetEntiteAdministratifFacade.edit(budgetEntiteAdministratif);
         super.remove(budgetEntiteAdministratifItem);
    }
    
    public void remove(CompteItem compteItem , List<BudgetEntiteAdministratifItem> budgetEntiteAdministratifItems){
        for (BudgetEntiteAdministratifItem budgetEntiteAdministratifItem : budgetEntiteAdministratifItems) {
                budgetEntiteAdministratifItem.setCompteItem(compteItem);
                remove(budgetEntiteAdministratifItem);
            }
    }
    
   
    
    public void create(BudgetEntiteAdministratifItem budgetEntiteAdministratifItem){
        BudgetEntiteAdministratif budgetEntiteAdministratif = budgetEntiteAdministratifFacade.find(budgetEntiteAdministratifItem.getBudgetEntiteAdministratif().getId());
        budgetEntiteAdministratif.setMontantEngage(budgetEntiteAdministratif.getMontantEngage() + budgetEntiteAdministratifItem.getCompteItem().getMontantAffecte());
        budgetEntiteAdministratif.setMontantPaye(budgetEntiteAdministratif.getMontantPaye() + budgetEntiteAdministratifItem.getCompteItem().getMontantPaye());
        budgetEntiteAdministratifFacade.edit(budgetEntiteAdministratif);
        super.create(budgetEntiteAdministratifItem);
    }
    
    public List<BudgetEntiteAdministratifItem> findByCompteItem(CompteItem compteItem){
        String requette = "SELECT beai FROM BudgetEntiteAdministratifItem beai WHERE beai.compteItem.id='"+compteItem.getId()+"'";
        return em.createQuery(requette).getResultList();
    }
    
    
    
}
