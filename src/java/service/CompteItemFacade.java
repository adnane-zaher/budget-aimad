/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BudgetEntiteAdministratif;
import bean.BudgetEntiteAdministratifItem;
import bean.CompteItem;
import java.util.ArrayList;
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
public class CompteItemFacade extends AbstractFacade<CompteItem> {

    @PersistenceContext(unitName = "budget_en_ligne_last_versionPU")
    private EntityManager em;

    @EJB
    private BudgetEntiteAdministratifItemFacade budgetEntiteAdministratifItemFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteItemFacade() {
        super(CompteItem.class);
    }

//    public int affecterBudgetItem(CompteItem compteItem){
//        
//    }
    public Long generateId() {
        Long maxId = (Long) em.createQuery("SELECT MAX(ci.id) FROM CompteItem ci").getSingleResult();
        return (maxId == null ? 1l : maxId + 1);
    }

    public void create(CompteItem compteItem) {
        super.create(compteItem);
    }

    public void remove(CompteItem compteItem) {
        super.remove(compteItem);
    }

//    public List<CompteItem> findAll(BudgetEntiteAdministratif budgetEntiteAdministratif) {
//
//        String requette = "SELECT ci FROM CompteItem ci,BudgetEntiteAdministratifItem beai WHERE ci.id = beai.compteItem.id AND beai.budgetEntiteAdministratif.id'" + budgetEntiteAdministratif.getId() + "'";
//        return em.createQuery(requette).getResultList();
//    }

    public List<CompteItem> findAll(BudgetEntiteAdministratif budgetEntiteAdministratif) {
        List<BudgetEntiteAdministratifItem> budgetEntiteAdministratifItems = budgetEntiteAdministratifItemFacade.findByBudgetEntiteAdministratif(budgetEntiteAdministratif);
        List<CompteItem> items = new ArrayList<>();
        for (BudgetEntiteAdministratifItem budgetEntiteAdministratifItem : budgetEntiteAdministratifItems) {
            CompteItem compteItem = find(budgetEntiteAdministratifItem.getCompteItem().getId());
            items.add(compteItem);
        }
        return items;
    }

}
