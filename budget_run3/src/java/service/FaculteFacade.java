/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Faculte;
import bean.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adnane
 */
@Stateless
public class FaculteFacade extends AbstractFacade<Faculte> {

    @EJB
    EntiteAdministratifFacade entiteAdministratifFacade;
    @EJB
    BudgetFaculteFacade budgetFaculteFacade;
    @PersistenceContext(unitName = "budget_run2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaculteFacade() {
        super(Faculte.class);
    }

    public Faculte findByUser(User user) {
        if (user != null && user.getLogin() != null) {
            String requette = "SELECT f FROM Faculte f, User u WHERE u.faculte.id=f.id and u.login='"+ user.getLogin()+"'";
            return (Faculte) em.createQuery(requette).getSingleResult();
        }
        return new Faculte();
        
    }

    public int initFaculteParams(Faculte faculte) {
        if (faculte != null && faculte.getId() != null) {
            faculte.setEntiteAdministratifs(entiteAdministratifFacade.findByFaculte(faculte));
            faculte.setBudgetFacultes(budgetFaculteFacade.findByFaculte(faculte));
            return 1;
        }
        return -1;
    }
}
