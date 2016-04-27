/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BudgetFaculte;
import bean.Faculte;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adnane
 */
@Stateless
public class BudgetFaculteFacade extends AbstractFacade<BudgetFaculte> {
    @PersistenceContext(unitName = "budget_en_ligne_last_versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BudgetFaculteFacade() {
        super(BudgetFaculte.class);
    }
    
     public List<BudgetFaculte> findByFaculte(Faculte faculte){
        if(faculte !=null && faculte.getId()!= null){
            String requette = "SELECT bf FROM BudgetFaculte bf WHERE bf.faculte.id ="+faculte.getId();
            return em.createQuery(requette).getResultList();
        }
        return new ArrayList();
    }
}
