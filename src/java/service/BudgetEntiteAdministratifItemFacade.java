/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BudgetEntiteAdministratif;
import bean.BudgetEntiteAdministratifItem;
import bean.CompteItem;
import bean.EntiteAdministratif;
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
    @PersistenceContext(unitName = "budget_en_ligne_last_versionPU")
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
        System.out.println(" ha lbudget t edita ");
        super.create(budgetEntiteAdministratifItem);
        System.out.println("seyed khrej be3d ma crea lina l3bitika ???");
    }
    
//    public List<BudgetEntiteAdministratifItem> findByCompteItem(CompteItem compteItem){
//        String requette = "SELECT beai FROM BudgetEntiteAdministratifItem beai WHERE beai.compteItem.id='"+compteItem.getId()+"'";
//        return em.createQuery(requette).getResultList();
//    }
    
    public List<BudgetEntiteAdministratifItem> findByBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif){
        String requette = "SELECT beai FROM BudgetEntiteAdministratifItem beai WHERE beai.budgetEntiteAdministratif.id='"+budgetEntiteAdministratif.getId()+"'";
        return em.createQuery(requette).getResultList();
    }
    
    
    //hamid 
    
    
    
    public List<BudgetEntiteAdministratifItem> findByCriteres(String annee, EntiteAdministratif entiteAdministratif, int valider) {

        System.out.println("sahbna dkhel el methoda te service ");
        BudgetEntiteAdministratif budgetEntiteAdministratif = budgetEntiteAdministratifFacade.findByAnneAndEntity(annee, entiteAdministratif);
        System.out.println(" hahowa jab lbudget orje3 ejib lista dial les item lifih ");
        String requette = "SELECT beai FROM BudgetEntiteAdministratifItem beai WHERE beai.budgetEntiteAdministratif.id =" + budgetEntiteAdministratif.getId() + " AND beai.valider = " + valider;
        System.out.println(" ha lista ta3t les item -->" + requette);
        return em.createQuery(requette).getResultList();
    }

    public List<BudgetEntiteAdministratifItem> findByCriteres(BudgetEntiteAdministratif budgetEntiteAdministratif, int valider) {

        String requette = "SELECT beai FROM BudgetEntiteAdministratifItem beai WHERE beai.budgetEntiteAdministratif.id ='" + budgetEntiteAdministratif.getId() + "' AND beai.valider = " + valider;
        System.out.println(" ha rquetta li katjbed lista tles item ---> " + requette);
        return em.createQuery(requette).getResultList();
    }
    
    public BudgetEntiteAdministratifItem findByCompteItem(CompteItem compteItem ){
        String requette = "SELECT beai FROM BudgetEntiteAdministratfItem beai WHERE beai.compteItem.id = "+compteItem.getId();
        return (BudgetEntiteAdministratifItem) em.createQuery(requette).getSingleResult();
    }
    
}
